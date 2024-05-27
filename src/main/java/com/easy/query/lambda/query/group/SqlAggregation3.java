package com.easy.query.lambda.query.group;

import com.easy.query.lambda.query.IAggregation;
import io.github.kiryu1223.expressionTree.delegate.Func3;
import io.github.kiryu1223.expressionTree.expressions.Expr;
import io.github.kiryu1223.expressionTree.expressions.ExprTree;

import java.math.BigDecimal;

public abstract class SqlAggregation3<T1, T2, T3> implements IAggregation
{
    private long count;
    private BigDecimal sum;
    private BigDecimal avg;
    private Object max;
    private Object min;

    public <R> long count(@Expr Func3<T1, T2, T3, R> expr)
    {
        throw new RuntimeException();
    }

    public <R> BigDecimal sum(@Expr Func3<T1, T2, T3, R> expr)
    {
        throw new RuntimeException();
    }

    public <R> BigDecimal avg(@Expr Func3<T1, T2, T3, R> expr)
    {
        throw new RuntimeException();
    }

    public <R> R max(@Expr Func3<T1, T2, T3, R> expr)
    {
        throw new RuntimeException();
    }

    public <R> R min(@Expr Func3<T1, T2, T3, R> expr)
    {
        throw new RuntimeException();
    }

    public <R> long count()
    {
        return count;
    }

    public <R> long count(int i)
    {
        return count;
    }

    public <R> long count(ExprTree<Func3<T1, T2, T3, R>> expr)
    {
        return count;
    }

    public <R> BigDecimal sum(ExprTree<Func3<T1, T2, T3, R>> expr)
    {
        return sum;
    }

    public <R> BigDecimal avg(ExprTree<Func3<T1, T2, T3, R>> expr)
    {
        return avg;
    }

    public <R> R min(ExprTree<Func3<T1, T2, T3, R>> expr)
    {
        return (R) min;
    }

    public <R> R max(ExprTree<Func3<T1, T2, T3, R>> expr)
    {
        return (R) max;
    }
}
