package com.easy.query.lambda.client;

import com.easy.query.core.api.client.EasyQueryClient;
import com.easy.query.core.bootstrapper.EasyQueryBootstrapper;
import com.easy.query.core.common.bean.DefaultFastBean;
import com.easy.query.core.common.bean.FastBean;
import com.easy.query.core.expression.sql.expression.factory.ExpressionFactory;
import com.easy.query.core.util.EasyBeanUtil;
import com.easy.query.lambda.cud.LDelete;
import com.easy.query.mysql.expression.MySQLQuerySQLExpression;
import com.easy.query.oracle.expression.OracleExpressionFactory;
import com.easy.query.lambda.cud.LUpdate;
import com.easy.query.lambda.db.DbType;
import com.easy.query.lambda.query.LQuery;
import com.easy.query.lambda.query.LQuery2;

import java.lang.invoke.MethodHandles;

public class EasyLambdaQueryClient
{
    private final EasyQueryClient easyQueryClient;
    private final DbType dbType;

    public EasyLambdaQueryClient(EasyQueryClient easyQueryClient,MethodHandles.Lookup lookup)
    {
        this.easyQueryClient = easyQueryClient;
        this.dbType = getDbTypeByEasyQueryClient(easyQueryClient);
        unsafeFastBean(lookup);
    }

    private void unsafeFastBean(MethodHandles.Lookup lookup)
    {
        EasyBeanUtil.FAST_BEAN_FUNCTION = (c) -> new UnsafeFastBean(c, lookup);
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

    public <T> LDelete<T> delete(Class<T> c)
    {
        return new LDelete<>(easyQueryClient.deletable(c), dbType);
    }

    public <T> LUpdate<T> updatable(Class<T> c)
    {
        return new LUpdate<>(easyQueryClient.updatable(c), dbType);
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
