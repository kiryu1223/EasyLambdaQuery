package com.easy.query.lambda.visitor;

import com.easy.query.lambda.util.ExpressionUtil;
import com.easy.query.lambda.util.SqlUtil;
import com.easy.query.lambda.db.DbType;
import com.easy.query.lambda.error.IllegalExpressionException;

import io.github.kiryu1223.expressionTree.expressions.*;
import java.util.*;

public class WhereVisitor extends BaseVisitor
{
    public WhereVisitor(List<ParameterExpression> parameters, DbType dbType)
    {
        super(parameters,dbType);
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
        Expression operand = unary.getOperand();
        OperatorType operatorType = unary.getOperatorType();
        String sqlOp = SqlUtil.toSqlOp(operatorType);
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
            if (parameters.contains(parameter) && !ExpressionUtil.isVoid(methodCall.getMethod().getReturnType()))
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
            if (parameters.contains(parameter) && !ExpressionUtil.isVoid(fieldSelect.getField().getType()))
            {
                int index = parameters.indexOf(parameter);
                putField(index, SqlUtil.fieldName(fieldSelect.getField()));
            }
            else
            {
                throw new IllegalExpressionException(fieldSelect);
            }
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
//            data.append(indexBlock());
//            if (value instanceof String)
//            {
//                if (nowIsContains.get())
//                {
//                    sqlValue.add(new SqlValue("%" + value + "%"));
//                }
//                else if (nowIsStartsWith.get())
//                {
//                    sqlValue.add(new SqlValue(value + "%"));
//                }
//                else if (nowIsEndsWith.get())
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
}
