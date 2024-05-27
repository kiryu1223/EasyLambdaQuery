package com.easy.query.lambda.test;

import com.easy.query.lambda.tempResult.Result;
import io.github.kiryu1223.expressionTree.delegate.Action0;
import io.github.kiryu1223.expressionTree.expressions.Expr;
import io.github.kiryu1223.expressionTree.expressions.ExprTree;


public class GetterTest
{
    public static void main(String[] args)
    {
        test(() ->
        {
            new Result()
            {
                int id;
            };
        });
    }

    static void test(@Expr Action0 action0)
    {

    }

    static void test(ExprTree<Action0> action0)
    {

    }
}
