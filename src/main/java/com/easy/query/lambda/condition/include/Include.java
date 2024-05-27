package com.easy.query.lambda.condition.include;

import com.easy.query.core.basic.api.select.ClientQueryable;
import com.easy.query.lambda.condition.criteria.Criteria;
import io.github.kiryu1223.expressionTree.expressions.LambdaExpression;
import com.easy.query.lambda.query.QueryData;
import com.easy.query.lambda.visitor.HavingVisitor;
import com.easy.query.lambda.visitor.SqlValue;

public class Include extends Criteria
{
    private final LambdaExpression<?> expression;
    private final int groupSize;

    public Include(LambdaExpression<?> expression)
    {
        this(expression,0);
    }

    public Include(LambdaExpression<?> expression, int groupSize)
    {
        checkExprBody(expression);
        this.expression = expression;
        this.groupSize = groupSize;
    }

    public void analysis(ClientQueryable<?> queryable, QueryData queryData)
    {
        HavingVisitor include = new HavingVisitor(expression.getParameters(), queryData);
        expression.getBody().accept(include);
        for (SqlValue sqlValue : include.getSqlValue())
        {
            if (groupSize > 0)
            {
                queryable.include(s -> s.with(sqlValue.value.toString(), groupSize));
            }
            else
            {
                queryable.include(s -> s.with(sqlValue.value.toString()));
            }
        }
    }
}
