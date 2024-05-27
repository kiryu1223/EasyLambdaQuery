package com.easy.query.lambda.condition.offset;


import com.easy.query.core.basic.api.select.ClientQueryable;
import com.easy.query.lambda.condition.criteria.Criteria;
import com.easy.query.lambda.query.QueryData;

public class Offset extends Criteria
{
    private final long offset;

    public Offset(long offset)
    {
        this.offset = offset;
    }

    public long getOffset()
    {
        return offset;
    }


    public void analysis(ClientQueryable<?> queryable, QueryData queryData)
    {

    }


//    @Override
//    public void analysis(SqlBuilder builder, QueryData queryData)
//    {
//        SqlBuilder.Offset sqlOffset = builder.offset;
//        if (sqlOffset.isEmpty())
//        {
//            sqlOffset.append("OFFSET").append(offset);
//        }
//    }
}
