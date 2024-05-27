package com.easy.query.lambda.query.group;

import com.easy.query.core.basic.api.select.ClientQueryable3;
import com.easy.query.lambda.condition.having.Having;
import com.easy.query.lambda.condition.orderby.OrderBy;
import com.easy.query.lambda.condition.select.Select;
import com.easy.query.lambda.query.QueryBase;
import com.easy.query.lambda.query.QueryData;
import io.github.kiryu1223.expressionTree.delegate.Func1;
import io.github.kiryu1223.expressionTree.expressions.*;
import com.easy.query.lambda.query.LQuery;


public class GroupedQuery3<Key, T1, T2, T3> extends QueryBase
{
    protected final ClientQueryable3<T1, T2, T3> clientQueryable3;

    public GroupedQuery3(ClientQueryable3<T1, T2, T3> clientQueryable3, QueryData queryData)
    {
        super(queryData);
        this.clientQueryable3 = clientQueryable3;
    }

    public GroupedQuery3<Key, T1, T2, T3> having(@Expr Func1<Group2<Key, T1, T2>, Boolean> func)
    {
        throw new RuntimeException();
    }

    public GroupedQuery3<Key, T1, T2, T3> having(ExprTree<Func1<Group2<Key, T1, T2>, Boolean>> expr)
    {
        Having having = new Having(expr.getTree());
        having.analysis(clientQueryable3, queryData);
        return this;
    }

    public <R> GroupedQuery3<Key, T1, T2, T3> orderBy(@Expr Func1<Group2<Key, T1, T2>, R> expr, boolean asc)
    {
        throw new RuntimeException();
    }

    public <R> GroupedQuery3<Key, T1, T2, T3> orderBy(ExprTree<Func1<Group2<Key, T1, T2>, R>> expr, boolean asc)
    {
        OrderBy orderBy = new OrderBy(expr.getTree(), asc);
        orderBy.analysis(clientQueryable3, queryData);
        return this;
    }

    public <R> GroupedQuery3<Key, T1, T2, T3> orderBy(@Expr Func1<Group2<Key, T1, T2>, R> expr)
    {
        throw new RuntimeException();
    }

    public <R> GroupedQuery3<Key, T1, T2, T3> orderBy(ExprTree<Func1<Group2<Key, T1, T2>, R>> expr)
    {
        OrderBy orderBy = new OrderBy(expr.getTree(), true);
        orderBy.analysis(clientQueryable3, queryData);
        return this;
    }

    public <R> LQuery<R> select(@Expr Func1<Group2<Key, T1, T2>, R> expr)
    {
        throw new RuntimeException();
    }

    public <R> LQuery<R> select(ExprTree<Func1<Group2<Key, T1, T2>, R>> expr)
    {
        Select select = new Select(expr.getTree());
        return new LQuery<>(select.analysis(clientQueryable3, queryData), queryData.getDbType());
    }
}
