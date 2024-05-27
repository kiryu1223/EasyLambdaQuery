package com.easy.query.lambda.query;


import static com.easy.query.lambda.util.SqlUtil.fieldName;

public abstract class QueryBase
{
    protected final QueryData queryData;

    public QueryBase(QueryData queryData)
    {
        this.queryData = queryData;
    }
}
