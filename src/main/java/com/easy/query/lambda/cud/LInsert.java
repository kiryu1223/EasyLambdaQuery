package com.easy.query.lambda.cud;

import com.easy.query.core.basic.api.insert.ClientInsertable;

import java.util.Collection;

public class LInsert<T>
{
    private final ClientInsertable<T> clientInsertable;

    public LInsert(ClientInsertable<T> clientInsertable)
    {
        this.clientInsertable = clientInsertable;
    }

    public LInsert<T> insert(T t)
    {
        clientInsertable.insert(t);
        return this;
    }

    public LInsert<T> insert(Collection<T> ts)
    {
        clientInsertable.insert(ts);
        return this;
    }

    public long executeRows()
    {
        return clientInsertable.executeRows();
    }

    public long executeRows(boolean fillAutoIncrement)
    {
        return clientInsertable.executeRows(fillAutoIncrement);
    }

}
