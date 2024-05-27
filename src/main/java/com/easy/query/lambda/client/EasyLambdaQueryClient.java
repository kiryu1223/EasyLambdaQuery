package com.easy.query.lambda.client;

import com.easy.query.core.api.client.EasyQueryClient;
import com.easy.query.core.expression.sql.expression.factory.ExpressionFactory;
import com.easy.query.mysql.expression.MySQLQuerySQLExpression;
import com.easy.query.oracle.expression.OracleExpressionFactory;
import com.easy.query.lambda.cud.LUpdate;
import com.easy.query.lambda.db.DbType;
import com.easy.query.lambda.query.LQuery;
import com.easy.query.lambda.query.LQuery2;


public class EasyLambdaQueryClient
{
    private final EasyQueryClient easyQueryClient;
    private final DbType dbType;

    public EasyLambdaQueryClient(EasyQueryClient easyQueryClient)
    {
        this.easyQueryClient = easyQueryClient;
        this.dbType = getDbTypeByEasyQueryClient(easyQueryClient);
    }

    private DbType getDbTypeByEasyQueryClient(EasyQueryClient easyQueryClient)
    {
        ExpressionFactory service = easyQueryClient.getRuntimeContext().getService(ExpressionFactory.class);
        if (service instanceof MySQLQuerySQLExpression)
        {
            return DbType.MySQL;
        }
        else if (service instanceof OracleExpressionFactory)
        {
            return DbType.Oracle;
        }
        else
        {
            return DbType.MySQL;
        }
    }

    public <T> LUpdate<T> updatable(Class<T> c)
    {
        return new LUpdate<>(easyQueryClient.updatable(c),dbType);
    }

    public <T> LQuery<T> queryable(Class<T> c)
    {
        return new LQuery<>(easyQueryClient.queryable(c), dbType);
    }

    public <T1, T2> LQuery2<T1, T2> queryable(Class<T1> c1, Class<T2> c2)
    {
        return new LQuery2<>(easyQueryClient.queryable(c1).from(c2), dbType);
    }
}
