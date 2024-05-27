package com.easy.query.lambda.test;


import com.easy.query.core.api.client.EasyQueryClient;
import com.easy.query.core.bootstrapper.EasyQueryBootstrapper;
import com.easy.query.core.logging.LogFactory;
import com.easy.query.lambda.client.EasyLambdaQueryClient;
import com.easy.query.lambda.test.pojo.Blog;
import com.easy.query.lambda.test.pojo.Topic;
import com.easy.query.mysql.config.MySQLDatabaseConfiguration;
import com.zaxxer.hikari.HikariDataSource;

import java.time.LocalDateTime;
import java.util.List;

public class Test
{
    public static void main(String[] args)
    {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl("jdbc:mysql://127.0.0.1:3306/newtest");
        dataSource.setUsername("root");
        dataSource.setPassword("108568");
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setMaximumPoolSize(20);

        LogFactory.useStdOutLogging();

        EasyQueryClient easyQueryClient = EasyQueryBootstrapper
                .defaultBuilderConfiguration()
                .setDefaultDataSource(dataSource)
                .useDatabaseConfigure(new MySQLDatabaseConfiguration())
                .build();

        EasyLambdaQueryClient eq = new EasyLambdaQueryClient(easyQueryClient);


        String sql = eq.updatable(Topic.class)
                .set(s ->
                {
                    s.setTitle("新标题");
                    s.setCreateTime(LocalDateTime.now());
                })
                .where(w -> 1 == 1)
                .toSql();

        System.out.println(sql);
    }
}
