package com.easy.query.lambda.visitor;

import com.easy.query.lambda.query.IAggregation;
import com.easy.query.lambda.query.IGroup;
import com.easy.query.lambda.query.QueryData;
import com.easy.query.lambda.util.ExpressionUtil;
import com.easy.query.lambda.util.GroupExtData;
import com.easy.query.lambda.util.SqlUtil;
import com.easy.query.lambda.error.IllegalExpressionException;
import io.github.kiryu1223.expressionTree.expressions.*;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;


public class HavingVisitor extends BaseVisitor
{

    private final QueryData queryData;

    public HavingVisitor(List<ParameterExpression> parameters, QueryData queryData)
    {
        super(parameters,queryData.getDbType());
        this.queryData = queryData;
    }

    @Override
    public void visit(LambdaExpression<?> lambda)
    {
        List<ParameterExpression> parameters = lambda.getParameters();
        HavingVisitor havingVisitor = new HavingVisitor(parameters, queryData);
        havingVisitor.mesIndex = mesIndex;
        lambda.getBody().accept(havingVisitor);
        data.append(havingVisitor.getData());
        sqlValue.addAll(havingVisitor.getSqlValue());
        mesIndex = havingVisitor.mesIndex;
    }

    @Override
    public void visit(ParensExpression parensExpression)
    {
        data.append("(");
        visit(parensExpression.getExpr());
        data.append(")");
    }

    @Override
    public void visit(UnaryExpression unary)
    {
        data.append(SqlUtil.toSqlOp(unary.getOperatorType()));
        Expression operand = unary.getOperand();
        String sqlOp = SqlUtil.toSqlOp(unary.getOperatorType());
        data.append(operand.getKind() == Kind.Parens ? sqlOp : sqlOp + "(");
        visit(operand);
        if (operand.getKind() != Kind.Parens)
        {
            data.append(")");
        }
    }

    @Override
    public void visit(BinaryExpression binary)
    {
        visit(binary.getLeft());
        data.append(" ").append(SqlUtil.toSqlOp(binary.getOperatorType())).append(" ");
        visit(binary.getRight());
    }

    @Override
    public void visit(ConstantExpression constant)
    {
        putValue(constant.getValue());
    }

    @Override
    public void visit(ReferenceExpression reference)
    {
        putValue(reference.getValue());
    }

    @Override
    public void visit(MethodCallExpression methodCall)
    {
        if (methodCall.getExpr().getKind() == Kind.Parameter)
        {
            ParameterExpression parameter = (ParameterExpression) methodCall.getExpr();
            if (IAggregation.class.isAssignableFrom(methodCall.getMethod().getDeclaringClass()))
            {
                Method method = methodCall.getMethod();
                List<Expression> args = methodCall.getArgs();
                String methodName = method.getName();
                data.append(methodName).append("(");
                if (args.isEmpty())
                {
                    data.append("*");
                }
                else
                {
                    for (Expression arg : args)
                    {
                        visit(arg);
                    }
                }
                data.append(")");
            }
            else if (parameters.contains(parameter) && !ExpressionUtil.isVoid(methodCall.getMethod().getReturnType()))
            {
                int index = parameters.indexOf(parameter);
                putField(index, SqlUtil.fieldName(methodCall.getMethod()));
            }
            else
            {
                throw new IllegalExpressionException(methodCall);
            }
        }
        else
        {
            methodCallVisitor(methodCall);
        }
    }

    @Override
    public void visit(FieldSelectExpression fieldSelect)
    {
        if (fieldSelect.getExpr().getKind() == Kind.Parameter)
        {
            ParameterExpression parameter = (ParameterExpression) fieldSelect.getExpr();
            Field field = fieldSelect.getField();
            if (parameters.contains(parameter))
            {
                if (IGroup.class.isAssignableFrom(field.getDeclaringClass())
                        && field.getName().equals("key"))
                {
                    GroupExtData groupExtData = queryData.getGroupExtDataMap().get("key");
                }
                else
                {
                    int index = parameters.indexOf(parameter);
                    String fieldName = SqlUtil.fieldName(field);
                    putField(index, fieldName);
                }
            }
            else
            {
                throw new IllegalExpressionException(fieldSelect);
            }
        }
        else if (ExpressionUtil.isGroupKey(parameters, fieldSelect.getExpr()))
        {
            Map<String, GroupExtData> gmap = queryData.getGroupExtDataMap();
            String fieldName = SqlUtil.fieldName(fieldSelect.getField());
            GroupExtData groupExtData = gmap.get(fieldName);
            putGroupValue(groupExtData);
        }
        else
        {
            tryPutExprValue(fieldSelect);
        }
    }

    @Override
    public void visit(ConditionalExpression conditional)
    {
        Expression condition = conditional.getCondition();
        Expression truePart = conditional.getTruePart();
        Expression falsePart = conditional.getFalsePart();
        Object value = condition.getValue();
        if (value != null)
        {
            if ((boolean) value)
            {
                visit(truePart);
            }
            else
            {
                visit(falsePart);
            }
        }
        else
        {
            data.append("IF(");
            visit(condition);
            data.append(",");
            visit(truePart);
            data.append(",");
            visit(falsePart);
            data.append(")");
        }
    }

//    private void appendIfStringOrNull(Object value)
//    {
//        if (value == null)
//        {
//            data.append("NULL");
//        }
//        else
//        {
//            data.append("?");
//            if (value instanceof String)
//            {
//                if (nowIsContains)
//                {
//                    sqlValue.add(new SqlValue("%" + value + "%"));
//                }
//                else if (nowIsStartsWith)
//                {
//                    sqlValue.add(new SqlValue(value + "%"));
//                }
//                else if (nowIsEndsWith)
//                {
//                    sqlValue.add(new SqlValue("%" + value));
//                }
//                else
//                {
//                    sqlValue.add(new SqlValue(value));
//                }
//            }
//            else
//            {
//                sqlValue.add(new SqlValue(value));
//            }
//        }
//    }

    private void putGroupValue(GroupExtData groupExtData)
    {
        StringBuilder tempStr = new StringBuilder();
        List<String> exprData = groupExtData.exprData;
        for (int i = 0; i < exprData.size(); i++)
        {
            String exprDatum = exprData.get(i);
            tempStr.append(exprDatum);
            if (i < exprData.size() - 1)
            {
                tempStr.append(indexBlock());
            }
        }
        data.append(tempStr);
        sqlValue.addAll(groupExtData.sqlValues);
    }
}
