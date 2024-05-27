package com.easy.query.lambda.query;

import com.easy.query.core.basic.api.select.ClientQueryable;
import com.easy.query.core.basic.api.select.ClientQueryable2;
import com.easy.query.core.common.ToSQLResult;
import com.easy.query.lambda.condition.groupBy.GroupBy;
import com.easy.query.lambda.condition.include.Include;
import com.easy.query.lambda.condition.limit.Limit;
import com.easy.query.lambda.condition.orderby.OrderBy;
import com.easy.query.lambda.condition.select.Select;
import com.easy.query.lambda.condition.where.Where;
import com.easy.query.lambda.db.DbType;
import com.easy.query.lambda.query.group.GroupedQuery;
import com.easy.query.lambda.visitor.SqlValue;
import com.easy.query.lambda.visitor.WhereVisitor;
import io.github.kiryu1223.expressionTree.delegate.Func1;
import io.github.kiryu1223.expressionTree.delegate.Func2;
import io.github.kiryu1223.expressionTree.expressions.*;

import java.util.*;

import static com.easy.query.lambda.util.SqlUtil.fieldName;


public class LQuery<T> extends QueryBase
{
    protected final ClientQueryable<T> clientQueryable;

    public LQuery(ClientQueryable<T> clientQueryable, DbType dbType)
    {
        super(new QueryData(dbType));
        this.clientQueryable = clientQueryable;
    }

    //    todo: 等别人提需求
//    public <T2> LQuery2<T, T2> from(LQuery<T2> t2)
//    {
//        ClientQueryable2<?, T2> from = clientQueryable.from(t2.clientQueryable);
//        return new LQuery2<>(from, queryData);
//    }

    //region [FROM]

    public <T2> LQuery2<T, T2> from(Class<T2> t2)
    {
        return new LQuery2<>(clientQueryable.from(t2),queryData.getDbType());
    }

    public <T2, T3> LQuery3<T, T2, T3> from(Class<T2> t2, Class<T3> t3)
    {
        return new LQuery3<>(clientQueryable.from(t2, t3),queryData.getDbType());
    }

    //endregion

    //region [JOIN]

    public <T2> LQuery2<T, T2> innerJoin(Class<T2> t2, @Expr Func2<T, T2, Boolean> func)
    {
        throw new RuntimeException();
    }

    public <T2> LQuery2<T, T2> innerJoin(Class<T2> t2, ExprTree<Func2<T, T2, Boolean>> expr)
    {
        WhereVisitor where = new WhereVisitor(expr.getTree().getParameters(), queryData.getDbType());
        expr.getTree().accept(where);

        ClientQueryable2<T, T2> clientQueryable2 = clientQueryable.innerJoin(t2, (w1, w2) ->
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
                                break;
                        }
                    }
                }));

        return new LQuery2<>(clientQueryable2,queryData.getDbType());
    }

    public <T2> LQuery2<T, T2> innerJoin(LQuery<T2> t2, @Expr Func2<T, T2, Boolean> func)
    {
        throw new RuntimeException();
    }

    public <T2> LQuery2<T, T2> innerJoin(LQuery<T2> t2, ExprTree<Func2<T, T2, Boolean>> expr)
    {
        WhereVisitor where = new WhereVisitor(expr.getTree().getParameters(), queryData.getDbType());
        expr.getTree().accept(where);

        //QueryParser.parser(t2.clientQueryable, t2.queryData);

        ClientQueryable2<T, T2> clientQueryable2 = clientQueryable.innerJoin(t2.clientQueryable, (w1, w2) ->
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
                                break;
                        }
                    }
                }));

        return new LQuery2<>(clientQueryable2,queryData.getDbType());
    }

    public <T2> LQuery2<T, T2> leftJoin(Class<T2> t2, @Expr Func2<T, T2, Boolean> func)
    {
        throw new RuntimeException();
    }

    public <T2> LQuery2<T, T2> leftJoin(Class<T2> t2, ExprTree<Func2<T, T2, Boolean>> expr)
    {
        WhereVisitor where = new WhereVisitor(expr.getTree().getParameters(), queryData.getDbType());
        expr.getTree().accept(where);

        ClientQueryable2<T, T2> clientQueryable2 = clientQueryable.leftJoin(t2, (w1, w2) ->
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
                                break;
                        }
                    }
                }));

        return new LQuery2<>(clientQueryable2,queryData.getDbType());
    }

    public <T2> LQuery2<T, T2> leftJoin(LQuery<T2> t2, @Expr Func2<T, T2, Boolean> func)
    {
        throw new RuntimeException();
    }

    public <T2> LQuery2<T, T2> leftJoin(LQuery<T2> t2, ExprTree<Func2<T, T2, Boolean>> expr)
    {
        WhereVisitor where = new WhereVisitor(expr.getTree().getParameters(), queryData.getDbType());
        expr.getTree().accept(where);

        //QueryParser.parser(t2.clientQueryable, t2.queryData);

        ClientQueryable2<T, T2> clientQueryable2 = clientQueryable.leftJoin(t2.clientQueryable, (w1, w2) ->
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
                                break;
                        }
                    }
                }));

        return new LQuery2<>(clientQueryable2,queryData.getDbType());
    }

    public <T2> LQuery2<T, T2> rightJoin(Class<T2> t2, @Expr Func2<T, T2, Boolean> func)
    {
        throw new RuntimeException();
    }

    public <T2> LQuery2<T, T2> rightJoin(Class<T2> t2, ExprTree<Func2<T, T2, Boolean>> expr)
    {
        WhereVisitor where = new WhereVisitor(expr.getTree().getParameters(), queryData.getDbType());
        expr.getTree().accept(where);

        ClientQueryable2<T, T2> clientQueryable2 = clientQueryable.rightJoin(t2, (w1, w2) ->
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
                                break;
                        }
                    }
                }));

        return new LQuery2<>(clientQueryable2,queryData.getDbType());
    }

    public <T2> LQuery2<T, T2> rightJoin(LQuery<T2> t2, @Expr Func2<T, T2, Boolean> func)
    {
        throw new RuntimeException();
    }

    public <T2> LQuery2<T, T2> rightJoin(LQuery<T2> t2, ExprTree<Func2<T, T2, Boolean>> expr)
    {
        WhereVisitor where = new WhereVisitor(expr.getTree().getParameters(), queryData.getDbType());
        expr.getTree().accept(where);

       // QueryParser.parser(t2.clientQueryable, t2.queryData);

        ClientQueryable2<T, T2> clientQueryable2 = clientQueryable.rightJoin(t2.clientQueryable, (w1, w2) ->
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
                                break;
                        }
                    }
                }));

        return new LQuery2<>(clientQueryable2,queryData.getDbType());
    }

    //endregion

    // region [WHERE]

    public LQuery<T> where(@Expr Func1<T, Boolean> func)
    {
        throw new RuntimeException();
    }

    public LQuery<T> where(ExprTree<Func1<T, Boolean>> expr)
    {
        Where where = new Where(expr.getTree());
        where.analysis(clientQueryable, queryData);
        return this;
    }

    // endregion

    // region [ORDER BY]

    public <R> LQuery<T> orderBy(@Expr Func1<T, R> expr, boolean asc)
    {
        throw new RuntimeException();
    }

    public <R> LQuery<T> orderBy(ExprTree<Func1<T, R>> expr, boolean asc)
    {
        OrderBy orderBy = new OrderBy(expr.getTree(), asc);
        orderBy.analysis(clientQueryable, queryData);
        return this;
    }

    public <R> LQuery<T> orderBy(@Expr Func1<T, R> expr)
    {
        throw new RuntimeException();
    }

    public <R> LQuery<T> orderBy(ExprTree<Func1<T, R>> expr)
    {
        OrderBy orderBy = new OrderBy(expr.getTree(), true);
        orderBy.analysis(clientQueryable, queryData);
        return this;
    }

    // endregion

    // region [LIMIT]

    public LQuery<T> limit(long rows)
    {
        Limit limit = new Limit(rows);
        limit.analysis(clientQueryable, queryData);
        return this;
    }

    public LQuery<T> limit(long offset, long rows)
    {
        Limit limit = new Limit(offset, rows);
        limit.analysis(clientQueryable, queryData);
        return this;
    }

    // endregion

    // region [GROUP BY]

    public <Key> GroupedQuery<Key, T> groupBy(@Expr Func1<T, Key> expr)
    {
        throw new RuntimeException();
    }

    public <Key> GroupedQuery<Key, T> groupBy(ExprTree<Func1<T, Key>> expr)
    {
        GroupBy groupBy = new GroupBy(expr.getTree());
        groupBy.analysis(clientQueryable, queryData);
        return new GroupedQuery<>(clientQueryable, queryData);
    }

    // endregion

    // region [SELECT]
    public LQuery<T> select()
    {
        ClientQueryable<T> select = clientQueryable.select(s -> s.columnAll());
        return new LQuery<>(select, queryData.getDbType());
    }

    public <R> LQuery<R> select(@Expr Func1<T, R> expr)
    {
        throw new RuntimeException();
    }

    public <R> LQuery<R> select(ExprTree<Func1<T, R>> expr)
    {
        Select select = new Select(expr.getTree());
        return new LQuery<>(select.analysis(clientQueryable, queryData), queryData.getDbType());
    }
    // endregion

    // region [INCLUDE]
    public <R> LQuery<T> include(@Expr Func1<T, R> expr,int groupSize)
    {
        throw new RuntimeException();
    }

    public <R> LQuery<T> include(ExprTree<Func1<T, R>> expr,int groupSize)
    {
        Include include = new Include(expr.getTree(), groupSize);
        include.analysis(clientQueryable,queryData);
        return this;
    }

    public <R> LQuery<T> include(@Expr Func1<T, R> expr)
    {
        throw new RuntimeException();
    }

    public <R> LQuery<T> include(ExprTree<Func1<T, R>> expr)
    {
        Include include = new Include(expr.getTree());
        include.analysis(clientQueryable,queryData);
        return this;
    }
    // endregion

    public String toSql()
    {
        return clientQueryable.toSQL();
    }

    public ToSQLResult toSQLResult()
    {
        return clientQueryable.toSQLResult();
    }

    public List<T> toList()
    {
        return clientQueryable.toList();
    }

    public <R> List<R> toList(Func1<T, R> func)
    {
        List<R> rList = new ArrayList<>();
        for (T t : toList())
        {
            rList.add(func.invoke(t));
        }
        return rList;
    }
}
