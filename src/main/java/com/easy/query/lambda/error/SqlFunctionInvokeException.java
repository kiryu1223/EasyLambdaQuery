package com.easy.query.lambda.error;

public class SqlFunctionInvokeException extends RuntimeException
{
    public SqlFunctionInvokeException()
    {
        super("SqlFunction不能在查询表达式以外的地方被调用");
    }
}
