package com.easy.query.lambda.condition.select;

import com.easy.query.core.basic.api.select.ClientQueryable;
import com.easy.query.core.basic.api.select.ClientQueryable2;
import com.easy.query.core.basic.api.select.ClientQueryable3;
import com.easy.query.lambda.condition.criteria.Criteria;
import io.github.kiryu1223.expressionTree.expressions.LambdaExpression;
import com.easy.query.lambda.query.QueryData;
import com.easy.query.lambda.visitor.SelectVisitor;
import com.easy.query.lambda.visitor.SqlValue;

public class Select extends Criteria
{
    private final LambdaExpression<?> expression;

    public Select(LambdaExpression<?> lambdaExpression)
    {
        this.expression = lambdaExpression;
    }

//    private void test(Class<?> entityClass)// 这是匿名类
//    {
//        try
//        {
//            // 可以拿到私有构造，但是会参数，参数类型是捕获的变量
//            Constructor<?> declaredConstructor = entityClass.getDeclaredConstructors()[0];
//
//            // unsafe可以无视条件生成一个对象（像调用无参构造一样）
//            Field field = Unsafe.class.getDeclaredField("theUnsafe");
//            field.setAccessible(true);
//            Unsafe unsafe = (Unsafe) field.get(null);
//
//            // 不会报错，后续可以继续反射obj
//            Object obj = unsafe.allocateInstance(entityClass);
//            System.out.println(obj);
//        }
//        catch (NoSuchFieldException | IllegalAccessException | InstantiationException e)
//        {
//            throw new RuntimeException(e);
//        }
//
//
//    }

    public <T, R> ClientQueryable<R> analysis(ClientQueryable<T> queryable, QueryData queryData)
    {
        //test(expression.getReturnType());
        SelectVisitor select = new SelectVisitor(expression.getParameters(), queryData);
        expression.getBody().accept(select);
        return queryable.select((Class<R>) expression.getReturnType(), w -> w.sqlNativeSegment(select.getData(), s ->
        {
            for (SqlValue sqlValue : select.getSqlValue())
            {
                switch (sqlValue.type)
                {
                    case value:
                        s.value(sqlValue.value);
                        break;
                    case property:
                        if (sqlValue.index == 0)
                        {
                            s.expression(sqlValue.value.toString());
                        }
                        break;
                }
            }
        }));

    }

    public <T1, T2, R> ClientQueryable<R> analysis(ClientQueryable2<T1, T2> queryable, QueryData queryData)
    {
        SelectVisitor select = new SelectVisitor(expression.getParameters(), queryData);
        expression.getBody().accept(select);
        return queryable.select((Class<R>) expression.getReturnType(), (w1, w2) -> w1.sqlNativeSegment(select.getData(), s ->
        {
            for (SqlValue sqlValue : select.getSqlValue())
            {
                switch (sqlValue.type)
                {
                    case value:
                        s.value(sqlValue.value);
                        break;
                    case property:
                        if (sqlValue.index == 0)
                        {
                            s.expression(w1, sqlValue.value.toString());
                        }
                        else if (sqlValue.index == 1)
                        {
                            s.expression(w2, sqlValue.value.toString());
                        }
                        break;
                }
            }
        }));
    }

    public <T1, T2, T3, R> ClientQueryable<R> analysis(ClientQueryable3<T1, T2, T3> queryable, QueryData queryData)
    {
        SelectVisitor select = new SelectVisitor(expression.getParameters(), queryData);
        expression.getBody().accept(select);
        return queryable.select((Class<R>) expression.getReturnType(), (w0, w1, w2) -> w0.sqlNativeSegment(select.getData(), s ->
        {
            for (SqlValue sqlValue : select.getSqlValue())
            {
                switch (sqlValue.type)
                {
                    case value:
                        s.value(sqlValue.value);
                        break;
                    case property:
                        if (sqlValue.index == 0)
                        {
                            s.expression(w0, sqlValue.value.toString());
                        }
                        else if (sqlValue.index == 1)
                        {
                            s.expression(w1, sqlValue.value.toString());
                        }
                        else if (sqlValue.index == 2)
                        {
                            s.expression(w2, sqlValue.value.toString());
                        }
                        break;
                }
            }
        }));
    }
}
