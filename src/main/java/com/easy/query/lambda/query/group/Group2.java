package com.easy.query.lambda.query.group;

import com.easy.query.lambda.query.IGroup;

public class Group2<Key, T1, T2> extends SqlAggregation2<T1, T2> implements IGroup
{
    public Key key;
}
