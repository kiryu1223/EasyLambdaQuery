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

public class SelectVisitor extends BaseVisitor
{
    private String temp = "";
    private final QueryData queryData;
    private ParameterExpression curParameter;

    public SelectVisitor(List<ParameterExpression> parameters, QueryData queryData)
    {
        super(parameters, queryData.getDbType());
        this.queryData = queryData;
    }

    @Override
    public void visit(NewExpression newExpression)
    {
        BlockExpression classBody = newExpression.getClassBody();
        if (classBody == null) return;
        List<Expression> expressions = classBody.getExpressions();
        for (int i = 0; i < expressions.size(); i++)
        {
            Expression expression = expressions.get(i);
            if (expression.getKind() == Kind.Variable)
            {
                VariableExpression variable = (VariableExpression) expression;
                visit(variable.getInit());
                String name = variable.getParameter().getName();
                if (!name.equals(temp))
                {
                    data.append(" AS ").append(name);
                }
                if (i < expressions.size() - 1)
                {
                    data.append(",");
                }
            }
        }
    }

    @Override
    public void visit(LambdaExpression<?> lambda)
    {
        List<ParameterExpression> parameters = lambda.getParameters();
        SelectVisitor selectVisitor = new SelectVisitor(parameters, queryData);
        selectVisitor.mesIndex = mesIndex;
        lambda.getBody().accept(selectVisitor);
        data.append(selectVisitor.getData());
        sqlValue.addAll(selectVisitor.getSqlValue());
        mesIndex = selectVisitor.mesIndex;
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
                temp = methodName + "()";
            }
            else if (parameters.contains(parameter) && !ExpressionUtil.isVoid(methodCall.getMethod().getReturnType()))
            {
                int index = parameters.indexOf(parameter);
                String fieldName = SqlUtil.fieldName(methodCall.getMethod());
                putField(index, fieldName);
                temp = fieldName;
            }
            else if (curParameter == parameter)
            {
                for (Expression arg : methodCall.getArgs())
                {
                    visit(arg);
                }
                String name = SqlUtil.fieldName(methodCall.getMethod());
                if (!name.equals(temp))
                {
                    data.append(" AS ").append(name);
                }
                data.append(",");
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
                    temp = putGroupValue(groupExtData);
                }
                else
                {
                    int index = parameters.indexOf(parameter);
                    String fieldName = SqlUtil.fieldName(field);
                    putField(index, fieldName);
                    temp = fieldName;
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
            temp = putGroupValue(groupExtData);
        }
        else
        {
            tryPutExprValue(fieldSelect);
        }
    }

    @Override
    public void visit(VariableExpression variableExpression)
    {
        curParameter = variableExpression.getParameter();
    }

//    @Override
//    public void visit(ParameterExpression parameter)
//    {
//        if (parameters.contains(parameter))
//        {
//            int index = parameters.indexOf(parameter);
//            data.append(indexBlock());
//            sqlValue.add(new SqlValue(SqlValue.Type.property, index, "*"));
//        }
//    }

    @Override
    public void visit(ConstantExpression constant)
    {
        addValue(constant.getValue());
    }

    @Override
    public void visit(ReferenceExpression reference)
    {
        addValue(reference.getValue());
    }

    @Override
    public void visit(ReturnExpression returnExpression)
    {
        int index = data.length() - 1;
        if (data.charAt(index) == ',')
        {
            data.deleteCharAt(index);
        }
    }

    private void addValue(Object value)
    {
        if (value == null)
        {
            data.append("NULL");
        }
        else
        {
            data.append(indexBlock());
            sqlValue.add(new SqlValue(value));
        }
    }

    private String putGroupValue(GroupExtData groupExtData)
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
        return tempStr.toString();
    }
}
