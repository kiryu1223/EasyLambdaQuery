package com.easy.query.lambda.query;

import com.easy.query.core.basic.api.select.ClientQueryable2;
import com.easy.query.core.basic.api.select.ClientQueryable3;
import com.easy.query.lambda.condition.groupBy.GroupBy;
import com.easy.query.lambda.condition.limit.Limit;
import com.easy.query.lambda.condition.orderby.OrderBy;
import com.easy.query.lambda.condition.select.Select;
import com.easy.query.lambda.condition.where.Where;
import com.easy.query.lambda.db.DbType;
import com.easy.query.lambda.visitor.SqlValue;
import com.easy.query.lambda.visitor.WhereVisitor;
import io.github.kiryu1223.expressionTree.delegate.Func2;
import io.github.kiryu1223.expressionTree.delegate.Func3;
import io.github.kiryu1223.expressionTree.expressions.*;
import com.easy.query.lambda.query.group.GroupedQuery2;

import static com.easy.query.lambda.util.SqlUtil.fieldName;


public class LQuery2<T1, T2> extends QueryBase
{
    protected final ClientQueryable2<T1, T2> clientQueryable2;

    public LQuery2(ClientQueryable2<T1, T2> clientQueryable2, DbType dbType)
    {
        super(new QueryData(dbType));
        this.clientQueryable2 = clientQueryable2;
    }

    //region [JOIN]
    public <T3> LQuery3<T1, T2, T3> innerJoin(Class<T3> t3, @Expr Func3<T1, T2, T3, Boolean> func)
    {
        throw new RuntimeException();
    }

    public <T3> LQuery3<T1, T2, T3> innerJoin(Class<T3> t3, ExprTree<Func3<T1, T2, T3, Boolean>> expr)
    {
        WhereVisitor where = new WhereVisitor(expr.getTree().getParameters(), queryData.getDbType());
        expr.getTree().accept(where);

        ClientQueryable3<T1, T2, T3> clientQueryable3 = clientQueryable2.innerJoin(t3, (w1, w2, w3) ->
        {
            w1.sqlNativeSegment(where.getData(), g ->
            {
                for (SqlValue sqlValue : where.getSqlValue())
                {
                    switch (sqlValue.type)
                    {
                        case value:
                            g.value(sqlValue.value);
                            break;
                        case property:
                            if (sqlValue.index == 0)
                            {
                                g.expression(w1, sqlValue.value.toString());
                            }
                            else if (sqlValue.index == 1)
                            {
                                g.expression(w2, sqlValue.value.toString());
                            }
                            else if (sqlValue.index == 2)
                            {
                                g.expression(w3, sqlValue.value.toString());
                            }
                            break;
                    }
                }
            });
        });

        return new LQuery3<>(clientQueryable3,queryData.getDbType());
    }

    public <T3> LQuery3<T1, T2, T3> innerJoin(LQuery<T3> t3, @Expr Func3<T1, T2, T3, Boolean> func)
    {
        throw new RuntimeException();
    }

    public <T3> LQuery3<T1, T2, T3> innerJoin(LQuery<T3> t3, ExprTree<Func3<T1, T2, T3, Boolean>> expr)
    {
        WhereVisitor where = new WhereVisitor(expr.getTree().getParameters(), queryData.getDbType());
        expr.getTree().accept(where);

        //QueryParser.parser(t3.clientQueryable, t3.queryData);

        ClientQueryable3<T1, T2, T3> clientQueryable3 = clientQueryable2.innerJoin(t3.clientQueryable, (w1, w2, w3) ->
        {
            w1.sqlNativeSegment(where.getData(), g ->
            {
                for (SqlValue sqlValue : where.getSqlValue())
                {
                    switch (sqlValue.type)
                    {
                        case value:
                            g.value(sqlValue.value);
                            break;
                        case property:
                            if (sqlValue.index == 0)
                            {
                                g.expression(w1, sqlValue.value.toString());
                            }
                            else if (sqlValue.index == 1)
                            {
                                g.expression(w2, sqlValue.value.toString());
                            }
                            else if (sqlValue.index == 2)
                            {
                                g.expression(w3, sqlValue.value.toString());
                            }
                            break;
                    }
                }
            });
        });

        return new LQuery3<>(clientQueryable3,queryData.getDbType());
    }

    public <T3> LQuery3<T1, T2, T3> leftJoin(Class<T3> t3, @Expr Func3<T1, T2, T3, Boolean> func)
    {
        throw new RuntimeException();
    }

    public <T3> LQuery3<T1, T2, T3> leftJoin(Class<T3> t3, ExprTree<Func3<T1, T2, T3, Boolean>> expr)
    {
        WhereVisitor where = new WhereVisitor(expr.getTree().getParameters(), queryData.getDbType());
        expr.getTree().accept(where);

        ClientQueryable3<T1, T2, T3> clientQueryable3 = clientQueryable2.leftJoin(t3, (w1, w2, w3) ->
        {
            w1.sqlNativeSegment(where.getData(), g ->
            {
                for (SqlValue sqlValue : where.getSqlValue())
                {
                    switch (sqlValue.type)
                    {
                        case value:
                            g.value(sqlValue.value);
                            break;
                        case property:
                            if (sqlValue.index == 0)
                            {
                                g.expression(w1, sqlValue.value.toString());
                            }
                            else if (sqlValue.index == 1)
                            {
                                g.expression(w2, sqlValue.value.toString());
                            }
                            else if (sqlValue.index == 2)
                            {
                                g.expression(w3, sqlValue.value.toString());
                            }
                            break;
                    }
                }
            });
        });

        return new LQuery3<>(clientQueryable3,queryData.getDbType());
    }

    public <T3> LQuery3<T1, T2, T3> leftJoin(LQuery<T3> t3, @Expr Func3<T1, T2, T3, Boolean> func)
    {
        throw new RuntimeException();
    }

    public <T3> LQuery3<T1, T2, T3> leftJoin(LQuery<T3> t3, ExprTree<Func3<T1, T2, T3, Boolean>> expr)
    {
        WhereVisitor where = new WhereVisitor(expr.getTree().getParameters(), queryData.getDbType());
        expr.getTree().accept(where);

        //QueryParser.parser(t3.clientQueryable, t3.queryData);

        ClientQueryable3<T1, T2, T3> clientQueryable3 = clientQueryable2.leftJoin(t3.clientQueryable, (w1, w2, w3) ->
        {
            w1.sqlNativeSegment(where.getData(), g ->
            {
                for (SqlValue sqlValue : where.getSqlValue())
                {
                    switch (sqlValue.type)
                    {
                        case value:
                            g.value(sqlValue.value);
                            break;
                        case property:
                            if (sqlValue.index == 0)
                            {
                                g.expression(w1, sqlValue.value.toString());
                            }
                            else if (sqlValue.index == 1)
                            {
                                g.expression(w2, sqlValue.value.toString());
                            }
                            else if (sqlValue.index == 2)
                            {
                                g.expression(w3, sqlValue.value.toString());
                            }
                            break;
                    }
                }
            });
        });

        return new LQuery3<>(clientQueryable3,queryData.getDbType());
    }

    public <T3> LQuery3<T1, T2, T3> rightJoin(Class<T3> t3, @Expr Func3<T1, T2, T3, Boolean> func)
    {
        throw new RuntimeException();
    }

    public <T3> LQuery3<T1, T2, T3> rightJoin(Class<T3> t3, ExprTree<Func3<T1, T2, T3, Boolean>> expr)
    {
        WhereVisitor where = new WhereVisitor(expr.getTree().getParameters(), queryData.getDbType());
        expr.getTree().accept(where);

        ClientQueryable3<T1, T2, T3> clientQueryable3 = clientQueryable2.rightJoin(t3, (w1, w2, w3) ->
        {
            w1.sqlNativeSegment(where.getData(), g ->
            {
                for (SqlValue sqlValue : where.getSqlValue())
                {
                    switch (sqlValue.type)
                    {
                        case value:
                            g.value(sqlValue.value);
                            break;
                        case property:
                            if (sqlValue.index == 0)
                            {
                                g.expression(w1, sqlValue.value.toString());
                            }
                            else if (sqlValue.index == 1)
                            {
                                g.expression(w2, sqlValue.value.toString());
                            }
                            else if (sqlValue.index == 2)
                            {
                                g.expression(w3, sqlValue.value.toString());
                            }
                            break;
                    }
                }
            });
        });

        return new LQuery3<>(clientQueryable3,queryData.getDbType());
    }

    public <T3> LQuery3<T1, T2, T3> rightJoin(LQuery<T3> t3, @Expr Func3<T1, T2, T3, Boolean> func)
    {
        throw new RuntimeException();
    }

    public <T3> LQuery3<T1, T2, T3> rightJoin(LQuery<T3> t3, ExprTree<Func3<T1, T2, T3, Boolean>> expr)
    {
        WhereVisitor where = new WhereVisitor(expr.getTree().getParameters(), queryData.getDbType());
        expr.getTree().accept(where);

        //QueryParser.parser(t3.clientQueryable, t3.queryData);

        ClientQueryable3<T1, T2, T3> clientQueryable3 = clientQueryable2.innerJoin(t3.clientQueryable, (w1, w2, w3) ->
        {
            w1.sqlNativeSegment(where.getData(), g ->
            {
                for (SqlValue sqlValue : where.getSqlValue())
                {
                    switch (sqlValue.type)
                    {
                        case value:
                            g.value(sqlValue.value);
                            break;
                        case property:
                            if (sqlValue.index == 0)
                            {
                                g.expression(w1, sqlValue.value.toString());
                            }
                            else if (sqlValue.index == 1)
                            {
                                g.expression(w2, sqlValue.value.toString());
                            }
                            else if (sqlValue.index == 2)
                            {
                                g.expression(w3, sqlValue.value.toString());
                            }
                            break;
                    }
                }
            });
        });

        return new LQuery3<>(clientQueryable3,queryData.getDbType());
    }
    // endregion

    // region [WHERE]
    public LQuery2<T1, T2> where(@Expr Func2<T1, T2, Boolean> func)
    {
        throw new RuntimeException();
    }

    public LQuery2<T1, T2> where(ExprTree<Func2<T1, T2, Boolean>> expr)
    {
        Where where = new Where(expr.getTree());
        where.analysis(clientQueryable2, queryData);
        return this;
    }
    // endregion

    // region [ORDER BY]
    public <R> LQuery2<T1, T2> orderBy(@Expr Func2<T1, T2, R> expr, boolean asc)
    {
        throw new RuntimeException();
    }

    public <R> LQuery2<T1, T2> orderBy(ExprTree<Func2<T1, T2, R>> expr, boolean asc)
    {
        OrderBy orderBy = new OrderBy(expr.getTree(), asc);
        orderBy.analysis(clientQueryable2, queryData);
        return this;
    }

    public <R> LQuery2<T1, T2> orderBy(@Expr Func2<T1, T2, R> expr)
    {
        throw new RuntimeException();
    }

    public <R> LQuery2<T1, T2> orderBy(ExprTree<Func2<T1, T2, R>> expr)
    {
        OrderBy orderBy = new OrderBy(expr.getTree(), true);
        orderBy.analysis(clientQueryable2, queryData);
        return this;
    }
    // endregion

    // region [LIMIT]
    public LQuery2<T1, T2> limit(long rows)
    {
        Limit limit = new Limit(rows);
        limit.analysis(clientQueryable2, queryData);
        return this;
    }

    public LQuery2<T1, T2> limit(long offset, long rows)
    {
        Limit limit = new Limit(offset, rows);
        limit.analysis(clientQueryable2, queryData);
        return this;
    }
    // endregion

    // region [GROUP BY]
    public <Key> GroupedQuery2<Key, T1, T2> groupBy(@Expr Func2<T1, T2, Key> expr)
    {
        throw new RuntimeException();
    }

    public <Key> GroupedQuery2<Key, T1, T2> groupBy(ExprTree<Func2<T1, T2, Key>> expr)
    {
        GroupBy groupBy = new GroupBy(expr.getTree());
        groupBy.analysis(clientQueryable2, queryData);
        return new GroupedQuery2<>(clientQueryable2, queryData);
    }
    // endregion

    // region [SELECT]
    public <R> LQuery<R> select(@Expr Func2<T1, T2, R> expr)
    {
        throw new RuntimeException();
    }

    public <R> LQuery<R> select(ExprTree<Func2<T1, T2, R>> expr)
    {
        Select select = new Select(expr.getTree());
        return new LQuery<>(select.analysis(clientQueryable2, queryData),queryData.getDbType());
    }
    // endregion

    public String toSql()
    {
        return clientQueryable2.toSQL();
    }
}
