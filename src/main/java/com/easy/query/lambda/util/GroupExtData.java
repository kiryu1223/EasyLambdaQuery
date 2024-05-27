package com.easy.query.lambda.util;

import com.easy.query.lambda.visitor.SqlValue;

import java.util.List;

public class GroupExtData
{
    public final List<String> exprData;
    public final List<SqlValue> sqlValues;

    public GroupExtData(List<String> exprData, List<SqlValue> sqlValues)
    {
        this.exprData = exprData;
        this.sqlValues = sqlValues;
    }

    @Override
    public String toString()
    {
        return "GroupExtData{" +
                "exprData=" + exprData +
                ", sqlValues=" + sqlValues +
                '}';
    }
}
