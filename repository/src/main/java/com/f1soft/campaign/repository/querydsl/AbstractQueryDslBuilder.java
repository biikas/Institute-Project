package com.f1soft.campaign.repository.querydsl;


import com.f1soft.campaign.repository.Util.SearchParameter;
import com.google.common.collect.ImmutableMap;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Operator;
import com.querydsl.core.types.Ops;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.Expressions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;

/*
 * @Author Rashim Dhaubanjar
 */
@Repository
public abstract class AbstractQueryDslBuilder extends Qentities {

    protected static final Logger log = LoggerFactory.getLogger(AbstractQueryDslBuilder.class);

    protected Map<String, Operator> operators = ImmutableMap.<String, Operator>builder()
            .put("::", Ops.STRING_CONTAINS)
            .put("==", Ops.EQ)
            .put("!=", Ops.NE)
            .put(">", Ops.GT)
            .put("<", Ops.LT)
            .put(">=", Ops.GOE)
            .put("<=", Ops.LOE)
            .put("NOT_NULL", Ops.IS_NOT_NULL)
            .put("&&", Ops.AND)
            .put("||", Ops.OR)
            .build();

    protected BooleanBuilder buildWhereClause(List<SearchParameter> searchParameterList, BooleanBuilder booleanBuilder) {

        for (SearchParameter param : searchParameterList) {
            if (StringUtils.hasLength(String.valueOf(param.getValue()))) {
                if (StringUtils.hasLength(String.valueOf(param.getCondition()))) {
                    if (String.valueOf(param.getCondition()).equalsIgnoreCase("||")) {
                        booleanBuilder.or(matchesProperty(param));
                    } else {
                        booleanBuilder.and(matchesProperty(param));
                    }
                } else {
                    booleanBuilder.and(matchesProperty(param));
                }
            }
        }
        return booleanBuilder;
    }

    protected Predicate matchesProperty(SearchParameter param) {
        return Expressions.predicate(operators.get(param.getOperator())
                , param.getPath()
                , Expressions.constant(param.getValue()));
    }
}
