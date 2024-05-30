package com.easy.query.lambda.condition.where;

import com.easy.query.core.basic.api.delete.ClientExpressionDeletable;
import com.easy.query.core.basic.api.select.ClientQueryable;
import com.easy.query.core.basic.api.select.ClientQueryable2;
import com.easy.query.core.basic.api.select.ClientQueryable3;
import com.easy.query.core.basic.api.update.ClientExpressionUpdatable;
import com.easy.query.lambda.condition.criteria.Criteria;
import io.github.kiryu1223.expressionTree.expressions.*;
import com.easy.query.lambda.query.QueryData;
import com.easy.query.lambda.visitor.SqlValue;
import com.easy.query.lambda.visitor.WhereVisitor;

public class Where extends Criteria
{
    private final LambdaExpression<?> expression;

    public Where(LambdaExpression<?> expression)
    {
        checkExprBody(expression);
        this.expression = expression;
    }

    public void analysis(ClientExpressionDeletable<?> deletable, QueryData queryData)
    {
        WhereVisitor where = new WhereVisitor(expression.getParameters(), queryData.getDbType());
        expression.getBody().accept(where);
        deletable.where(w -> w.sqlNativeSegment(where.getData(), s ->
        {
            for (SqlValue sqlValue : where.getSqlValue())
            {
                switch (sqlValue.type)
                {
                    case value:
                        s.value(sqlValue.value);
                        break;
                    case property:
                        s.expression(sqlValue.value.toString());
                        break;
                }
            }
        }));
    }

    public void analysis(ClientExpressionUpdatable<?> updatable, QueryData queryData)
    {
        WhereVisitor where = new WhereVisitor(expression.getParameters(), queryData.getDbType());
        expression.getBody().accept(where);
        updatable.where(w -> w.sqlNativeSegment(where.getData(), s ->
        {
            for (SqlValue sqlValue : where.getSqlValue())
            {
                switch (sqlValue.type)
                {
                    case value:
                        s.value(sqlValue.value);
                        break;
                    case property:
                        s.expression(sqlValue.value.toString());
                        break;
                }
            }
        }));
    }

    public void analysis(ClientQueryable<?> queryable, QueryData queryData)
    {
        WhereVisitor where = new WhereVisitor(expression.getParameters(), queryData.getDbType());
        expression.getBody().accept(where);
        queryable.where(w -> w.sqlNativeSegment(where.getData(), s ->
        {
            for (SqlValue sqlValue : where.getSqlValue())
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
        WhereVisitor where = new WhereVisitor(expression.getParameters(), queryData.getDbType());
        expression.getBody().accept(where);
        queryable.where((w0, w1) -> w0.sqlNativeSegment(where.getData(), s ->
        {
            for (SqlValue sqlValue : where.getSqlValue())
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

    public void analysis(ClientQueryable3<?, ?, ?> queryable, QueryData queryData)
    {
        WhereVisitor where = new WhereVisitor(expression.getParameters(), queryData.getDbType());
        expression.getBody().accept(where);
        queryable.where((w0, w1, w2) -> w0.sqlNativeSegment(where.getData(), s ->
        {
            for (SqlValue sqlValue : where.getSqlValue())
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
