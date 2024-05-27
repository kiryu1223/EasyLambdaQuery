package com.easy.query.lambda.query;

import com.easy.query.core.basic.api.select.ClientQueryable3;
import com.easy.query.lambda.condition.groupBy.GroupBy;
import com.easy.query.lambda.condition.limit.Limit;
import com.easy.query.lambda.condition.orderby.OrderBy;
import com.easy.query.lambda.condition.select.Select;
import com.easy.query.lambda.condition.where.Where;
import com.easy.query.lambda.db.DbType;
import io.github.kiryu1223.expressionTree.delegate.Func3;
import io.github.kiryu1223.expressionTree.expressions.*;
import com.easy.query.lambda.query.group.GroupedQuery3;

import static com.easy.query.lambda.util.SqlUtil.fieldName;

public class LQuery3<T1, T2, T3> extends QueryBase
{
    protected final ClientQueryable3<T1, T2, T3> clientQueryable3;

    public LQuery3(ClientQueryable3<T1, T2, T3> clientQueryable3, DbType dbType)
    {
        super(new QueryData(dbType));
        this.clientQueryable3 = clientQueryable3;
    }

    // region [WHERE]
    public LQuery3<T1, T2, T3> where(@Expr Func3<T1, T2, T3, Boolean> func)
    {
        throw new RuntimeException();
    }

    public LQuery3<T1, T2, T3> where(ExprTree<Func3<T1, T2, T3, Boolean>> expr)
    {
        Where where = new Where(expr.getTree());
        where.analysis(clientQueryable3, queryData);
        return this;
    }
    // endregion

    // region [ORDER BY]
    public <R> LQuery3<T1, T2, T3> orderBy(@Expr Func3<T1, T2, T3, R> expr, boolean asc)
    {
        throw new RuntimeException();
    }

    public <R> LQuery3<T1, T2, T3> orderBy(ExprTree<Func3<T1, T2, T3, R>> expr, boolean asc)
    {
        OrderBy orderBy = new OrderBy(expr.getTree(), asc);
        orderBy.analysis(clientQueryable3, queryData);
        return this;
    }

    public <R> LQuery3<T1, T2, T3> orderBy(@Expr Func3<T1, T2, T3, R> expr)
    {
        throw new RuntimeException();
    }

    public <R> LQuery3<T1, T2, T3> orderBy(ExprTree<Func3<T1, T2, T3, R>> expr)
    {
        OrderBy orderBy = new OrderBy(expr.getTree(), true);
        orderBy.analysis(clientQueryable3, queryData);
        return this;
    }
    // endregion

    // region [LIMIT]
    public LQuery3<T1, T2, T3> limit(long rows)
    {
        Limit limit = new Limit(rows);
        limit.analysis(clientQueryable3, queryData);
        return this;
    }

    public LQuery3<T1, T2, T3> limit(long offset, long rows)
    {
        Limit limit = new Limit(offset, rows);
        limit.analysis(clientQueryable3, queryData);
        return this;
    }
    // endregion

    // region [GROUP BY]
    public <Key> GroupedQuery3<Key, T1, T2, T3> groupBy(@Expr Func3<T1, T2, T3, Key> expr)
    {
        throw new RuntimeException();
    }

    public <Key> GroupedQuery3<Key, T1, T2, T3> groupBy(ExprTree<Func3<T1, T2, T3, Key>> expr)
    {
        GroupBy groupBy = new GroupBy(expr.getTree());
        groupBy.analysis(clientQueryable3, queryData);
        return new GroupedQuery3<>(clientQueryable3, queryData);
    }
    // endregion

    // region [SELECT]
    public <R> LQuery<R> select(@Expr Func3<T1, T2, T3, R> expr)
    {
        throw new RuntimeException();
    }

    public <R> LQuery<R> select(ExprTree<Func3<T1, T2, T3, R>> expr)
    {
        Select select = new Select(expr.getTree());
        return new LQuery<>(select.analysis(clientQueryable3, queryData), queryData.getDbType());
    }
    // endregion

    public String toSql()
    {
        return clientQueryable3.toSQL();
    }
}
