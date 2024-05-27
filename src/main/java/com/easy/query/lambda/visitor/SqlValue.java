package com.easy.query.lambda.visitor;

public class SqlValue
{
    public enum Type
    {
        value,
        property,
    }

    public final Type type;
    public final int index;
    public final Object value;

    public SqlValue(Type valueType, int index, Object value)
    {
        this.type = valueType;
        this.index = index;
        this.value = value;
    }

    public SqlValue(Object value)
    {
        this(Type.value, -1, value);
    }

    public SqlValue(Type type,Object value)
    {
        this(type, -1, value);
    }

    @Override
    public String toString()
    {
        return "SqlValue{" +
                "type=" + type +
                ", index=" + index +
                ", value=" + value +
                '}';
    }
}
