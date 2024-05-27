package com.easy.query.lambda.visitor;

import com.easy.query.lambda.util.SqlUtil;
import com.easy.query.lambda.db.DbType;
import com.easy.query.lambda.error.IllegalExpressionException;
import io.github.kiryu1223.expressionTree.expressions.*;

import java.util.List;

import static com.easy.query.lambda.util.ExpressionUtil.*;


public class GroupByVisitor extends BaseVisitor
{
    public GroupByVisitor(List<ParameterExpression> parameters, DbType dbType)
    {
        super(parameters, dbType);
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
                if (i < expressions.size() - 1)
                {
                    data.append(",");
                }
            }
        }
    }

    @Override
    public void visit(MethodCallExpression methodCall)
    {
        if (methodCall.getExpr().getKind() == Kind.Parameter)
        {
            ParameterExpression parameter = (ParameterExpression) methodCall.getExpr();
            if (parameters.contains(parameter) && !isVoid(methodCall.getMethod().getReturnType()))
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
            if (parameters.contains(parameter) && !isVoid(fieldSelect.getField().getType()))
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
}
