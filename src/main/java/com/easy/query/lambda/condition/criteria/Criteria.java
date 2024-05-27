package com.easy.query.lambda.condition.criteria;


import com.easy.query.lambda.error.IllegalExpressionException;
import io.github.kiryu1223.expressionTree.expressions.Kind;
import io.github.kiryu1223.expressionTree.expressions.LambdaExpression;

public abstract class Criteria
{
    protected void checkExprBody(LambdaExpression<?> lambdaExpression)
    {
        if (lambdaExpression.getBody().getKind() == Kind.Block)
        {
            throw new IllegalExpressionException(lambdaExpression);
        }
    }
}
