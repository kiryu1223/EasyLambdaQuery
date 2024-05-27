package com.easy.query.lambda.query.group;

import com.easy.query.lambda.query.IGroup;

public class Group<Key,T> extends SqlAggregation<T> implements IGroup
{
    public Key key;
}
