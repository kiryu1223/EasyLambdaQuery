package com.easy.query.lambda.visitor;

import com.easy.query.lambda.db.DbType;
import com.easy.query.lambda.error.IllegalExpressionException;
import com.easy.query.lambda.ext.SqlFunctions;
import com.easy.query.lambda.util.ExpressionUtil;
import com.easy.query.lambda.util.ParamMatcher;
import com.easy.query.lambda.util.SqlUtil;
import io.github.kiryu1223.expressionTree.expressions.*;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SetVisitor extends BaseVisitor
{
    public SetVisitor(List<ParameterExpression> parameters, DbType dbType)
    {
        super(parameters, dbType);
    }

    private final List<Pair> pairs = new ArrayList<>();

    private Pair curPair;

    public List<Pair> getPairs()
    {
        return pairs;
    }

    private void checkHasPair(Expression expression)
    {
        if (curPair == null) throw new RuntimeException(expression.toString());
    }

    @Override
    public void visit(MethodCallExpression methodCall)
    {
        if (methodCall.getExpr().getKind() == Kind.Parameter)
        {
            ParameterExpression parameter = (ParameterExpression) methodCall.getExpr();
            if (parameters.contains(parameter))
            {
                curPair = new Pair();
                curPair.property = SqlUtil.fieldName(methodCall.getMethod());
                for (Expression arg : methodCall.getArgs())
                {
                    visit(arg);
                }
                pairs.add(curPair);
                cleanIndexBlock();
            }
            else
            {
                throw new IllegalExpressionException(methodCall);
            }
        }
        else
        {
            checkHasPair(methodCall);
            StringBuilder sqlSegment = curPair.sqlSegment;
            Method callMethod = methodCall.getMethod();
            Class<?> declaringClass = callMethod.getDeclaringClass();

            if (SqlFunctions.class.isAssignableFrom(declaringClass))
            {
                if (!callMethod.isAnnotationPresent(SqlFunctions.Ext.class))
                {
                    sqlSegment.append(callMethod.getName()).append("(");
                    List<Expression> args = methodCall.getArgs();
                    for (int i = 0; i < args.size(); i++)
                    {
                        Expression arg = args.get(i);
                        visit(arg);
                        if (i < args.size() - 1) sqlSegment.append(",");
                    }
                    sqlSegment.append(")");
                }
                else
                {
                    String function = getSqFuncExtByMethod(callMethod).function();
                    tryReplace(methodCall, function, sqlSegment);
                }
            }
            else
            {
                if (ExpressionUtil.hasParameter(methodCall))
                {
                    throw new RuntimeException(methodCall.toString());
                }
                sqlSegment.append(indexBlock());
                curPair.sqlValue.add(new SqlValue(methodCall.getValue()));
            }
        }
    }

    @Override
    public void visit(ConstantExpression constant)
    {
        checkHasPair(constant);
        curPair.sqlSegment.append(indexBlock());
        curPair.sqlValue.add(new SqlValue(constant.getValue()));
    }

    @Override
    public void visit(ReferenceExpression reference)
    {
        checkHasPair(reference);
        curPair.sqlSegment.append(indexBlock());
        curPair.sqlValue.add(new SqlValue(reference.getValue()));
    }
}
