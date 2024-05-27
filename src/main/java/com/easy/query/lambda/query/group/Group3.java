package com.easy.query.lambda.query.group;

import com.easy.query.lambda.query.IGroup;

public class Group3<Key, T1, T2, T3> extends SqlAggregation3<T1, T2, T3> implements IGroup
{
    public Key key;
}
