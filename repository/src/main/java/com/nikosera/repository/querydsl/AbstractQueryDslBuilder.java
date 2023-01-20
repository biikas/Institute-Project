package com.nikosera.repository.querydsl;

import com.google.common.collect.ImmutableMap;
import com.querydsl.core.types.Operator;
import com.querydsl.core.types.Ops;

import java.util.Map;

/**
 * @author Narayan Joshi
 * @email narenzoshi@gmail.com
 */
public class AbstractQueryDslBuilder {

    protected Map<OperatorEnum, Operator> operators = ImmutableMap.<OperatorEnum, Operator>builder()
            .put(OperatorEnum.LIKE, Ops.STRING_CONTAINS)
            .put(OperatorEnum.EQUALS, Ops.EQ)
            .put(OperatorEnum.NEGATE, Ops.NE)
            .put(OperatorEnum.GREATER_THAN, Ops.GT)
            .put(OperatorEnum.LESS_THAN, Ops.LT)
            .put(OperatorEnum.GREATER_THAN_AND_EQUAL, Ops.GOE)
            .put(OperatorEnum.LESS_THAN_AND_EQUAL, Ops.LOE)
            .put(OperatorEnum.IS_NOT_NULL, Ops.IS_NOT_NULL)
            .put(OperatorEnum.AND, Ops.AND)
            .put(OperatorEnum.OR, Ops.OR)
            .build();
}
