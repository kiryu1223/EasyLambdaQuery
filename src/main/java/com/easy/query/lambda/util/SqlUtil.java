package com.easy.query.lambda.util;

import com.easy.query.core.annotation.Column;
import com.easy.query.lambda.ext.SqlOperator;
import io.github.kiryu1223.expressionTree.expressions.OperatorType;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class SqlUtil
{
//    public static String indexTo(int i)
//    {
//        return String.valueOf((char) ('a' + i));
//    }

    public static String fieldName(Method method)
    {
        try
        {
            String fieldName = method.getName().substring(3);
            fieldName = fieldName.substring(0, 1).toLowerCase() + fieldName.substring(1);
            Class<?> declaringClass = method.getDeclaringClass();
            Field declaredField = declaringClass.getDeclaredField(fieldName);
            return fieldName(declaredField);
        }
        catch (NoSuchFieldException e)
        {
            throw new RuntimeException(e);
        }
    }

    public static String fieldName(Field field)
    {
        Column column = field.getAnnotation(Column.class);
        if (column == null || column.value().isEmpty())
        {
            return field.getName();
        }
        else
        {
            return column.value();
        }
    }

    public static String toSqlOp(OperatorType operatorType)
    {
        switch (operatorType)
        {
            case NOT:
                return "NOT";
            case AND:
                return "AND";
            case OR:
                return "OR";
            case EQ:
                return "=";
            case NE:
                return "<>";
            default:
                return operatorType.getOperator();
        }
    }

    public static String toSqlOp(SqlOperator operator)
    {
        return operator.getOperator();
    }
}
