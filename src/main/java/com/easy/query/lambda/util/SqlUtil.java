package com.easy.query.lambda.util;

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
        String name = method.getName().substring(3);
        return name.substring(0, 1).toLowerCase() + name.substring(1);
    }

    public static String fieldName(Field field)
    {
        return field.getName();
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
