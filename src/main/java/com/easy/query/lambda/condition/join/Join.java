package com.easy.query.lambda.condition.join;

import com.easy.query.core.basic.api.select.ClientQueryable;
import com.easy.query.lambda.condition.criteria.Criteria;
import com.easy.query.lambda.query.QueryData;

public class Join extends Criteria
{
//    private final ISource<?> source;
//    protected final JoinType joinType;
//    protected final LambdaExpression<?> expression;
//    protected final int index;
//
//    public Join(ISource<?> source, JoinType joinType, LambdaExpression<?> expression, int index)
//    {
//        this.source = source;
//        this.joinType = joinType;
//        this.expression = expression;
//        this.index = index;
//    }
//
//    public JoinType getJoinType()
//    {
//        return joinType;
//    }
//
//    public ISource<?> getSource()
//    {
//        return source;
//    }

//    @Override
//    public void analysis(SqlBuilder builder, QueryData queryData)
//    {
//        SqlBuilder.Join sqlJoin = builder.join;
//        switch (source.getKind())
//        {
//            case Table:
//                sqlJoin.append(joinType + " JOIN").append(source.getTableClass().getSimpleName())
//                        .append("AS " + get(index));
//                break;
//            case VirtualTable:
//                sqlJoin.append(joinType + " JOIN");
//                sqlJoin.append("(");
//                SqlData sqlData = getAnalysis().getSqlData(source.getQueryData());
//                sqlJoin.moveSqlAndValue(sqlData);
//                sqlJoin.append(") AS " + get(index));
//                break;
//        }
//        if (expression == null) return;
//        sqlJoin.append("ON");
//        List<ParameterExpression> parameters = expression.getParameters();
//        Expression body = expression.getBody();
//        body.accept(new ConditionalMappingVisitor(sqlJoin, parameters, queryData));
//    }


    public void analysis(ClientQueryable<?> queryable, QueryData queryData)
    {
    }
}
