package com.easy.query.lambda.condition.groupBy;

import com.easy.query.core.basic.api.select.ClientQueryable;
import com.easy.query.core.basic.api.select.ClientQueryable2;
import com.easy.query.core.basic.api.select.ClientQueryable3;
import com.easy.query.lambda.condition.criteria.Criteria;
import com.easy.query.lambda.error.IllegalExpressionException;
import com.easy.query.lambda.ext.SqlFunctions;
import io.github.kiryu1223.expressionTree.expressions.*;
import com.easy.query.lambda.query.QueryData;
import com.easy.query.lambda.util.GroupExtData;
import com.easy.query.lambda.visitor.GroupByVisitor;
import com.easy.query.lambda.visitor.SqlValue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.easy.query.lambda.util.ExpressionUtil.isTableProperty;
import static com.easy.query.lambda.util.SqlUtil.fieldName;

public class GroupBy extends Criteria
{
    private final LambdaExpression<?> expression;

    public GroupBy(LambdaExpression<?> expression)
    {
        checkExprBody(expression);
        this.expression = expression;
    }

    /**
     * 记录group字段，以便给select阶段使用
     */
    protected void readGroupBy(LambdaExpression<?> lambdaExpression, QueryData queryData)
    {
        Map<String, GroupExtData> groupExtDataMap = new HashMap<>();
        List<ParameterExpression> parameters = lambdaExpression.getParameters();

        lambdaExpression.getBody().accept(new DeepFindVisitor()
        {
            String key = "key";
            StringBuilder expr = new StringBuilder();
            List<String> exprData = new ArrayList<>();
            List<SqlValue> sqlValues = new ArrayList<>();

            @Override
            public void visit(MethodCallExpression methodCall)
            {
                if (isTableProperty(parameters, methodCall))
                {
                    ParameterExpression parameter = (ParameterExpression) methodCall.getExpr();
                    int index = parameters.indexOf(parameter);
                    exprData.add(expr.toString());
                    expr.setLength(0);
                    sqlValues.add(new SqlValue(SqlValue.Type.property, index, fieldName(methodCall.getMethod())));
                }
                else if (SqlFunctions.class.isAssignableFrom(methodCall.getMethod().getDeclaringClass()))
                {
                    expr.append(methodCall.getMethod().getName()).append("(");
                    for (Expression arg : methodCall.getArgs())
                    {
                        visit(arg);
                    }
                    expr.append(")");
                }
            }

            @Override
            public void visit(FieldSelectExpression fieldSelect)
            {
                if (isTableProperty(parameters, fieldSelect))
                {
                    ParameterExpression parameter = (ParameterExpression) fieldSelect.getExpr();
                    int index = parameters.indexOf(parameter);
                    exprData.add(expr.toString());
                    expr.setLength(0);
                    sqlValues.add(new SqlValue(SqlValue.Type.property, index, fieldName(fieldSelect.getField())));
                }
            }

            @Override
            public void visit(VariableExpression variable)
            {
                key = variable.getName();
                Expression init = variable.getInit();

                switch (init.getKind())
                {
                    case MethodCall:
                    case FieldSelect:
                        visit(init);
                        break;
                    default:
                        throw new IllegalExpressionException(variable);
                }
                if (expr.length() != 0)
                {
                    exprData.add(expr.toString());
                    expr.setLength(0);
                }
                groupExtDataMap.put(key, new GroupExtData(exprData, sqlValues));

                exprData = new ArrayList<>();
                sqlValues = new ArrayList<>();
            }
        });
        queryData.setGroupExtDataMap(groupExtDataMap);
    }

    public void analysis(ClientQueryable<?> queryable, QueryData queryData)
    {
        readGroupBy(expression, queryData);

        GroupByVisitor groupBy = new GroupByVisitor(expression.getParameters(), queryData.getDbType());
        expression.getBody().accept(groupBy);
        queryable.groupBy(w -> w.sqlNativeSegment(groupBy.getData(), s ->
        {
            for (SqlValue sqlValue : groupBy.getSqlValue())
            {
                switch (sqlValue.type)
                {
                    case value:
                        s.value(sqlValue.value);
                        break;
                    case property:
                        if (sqlValue.index == 0)
                        {
                            s.expression(sqlValue.value.toString());
                        }
                        break;
                }
            }
        }));
    }

    public void analysis(ClientQueryable2<?, ?> queryable, QueryData queryData)
    {
        readGroupBy(expression, queryData);

        GroupByVisitor groupBy = new GroupByVisitor(expression.getParameters(), queryData.getDbType());
        expression.getBody().accept(groupBy);
        queryable.groupBy((w0, w1) -> w0.sqlNativeSegment(groupBy.getData(), s ->
        {
            for (SqlValue sqlValue : groupBy.getSqlValue())
            {
                switch (sqlValue.type)
                {
                    case value:
                        s.value(sqlValue.value);
                        break;
                    case property:
                        if (sqlValue.index == 0)
                        {
                            s.expression(w0, sqlValue.value.toString());
                        }
                        else if (sqlValue.index == 1)
                        {
                            s.expression(w1, sqlValue.value.toString());
                        }
                        break;
                }
            }
        }));

    }

    public void analysis(ClientQueryable3<?, ?,?> queryable, QueryData queryData)
    {
        readGroupBy(expression, queryData);

        GroupByVisitor groupBy = new GroupByVisitor(expression.getParameters(), queryData.getDbType());
        expression.getBody().accept(groupBy);
        queryable.groupBy((w0, w1,w2) -> w0.sqlNativeSegment(groupBy.getData(), s ->
        {
            for (SqlValue sqlValue : groupBy.getSqlValue())
            {
                switch (sqlValue.type)
                {
                    case value:
                        s.value(sqlValue.value);
                        break;
                    case property:
                        if (sqlValue.index == 0)
                        {
                            s.expression(w0, sqlValue.value.toString());
                        }
                        else if (sqlValue.index == 1)
                        {
                            s.expression(w1, sqlValue.value.toString());
                        }
                        else if (sqlValue.index == 2)
                        {
                            s.expression(w2, sqlValue.value.toString());
                        }
                        break;
                }
            }
        }));

    }
}
