package com.nikosera.repository.querydsl;

import com.nikosera.repository.querydsl.utils.QueryParam;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.Expressions;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @author Narayan Joshi
 * @email narenzoshi@gmail.com
 */
public abstract class GenericSearch<T> extends AbstractQueryDslBuilder {

    public abstract BooleanBuilder buildPredicate(T search);

    protected BooleanBuilder buildWhereClause(List<QueryParam> queryParams) {
        BooleanBuilder booleanBuilder = new BooleanBuilder();

        for (QueryParam param : queryParams) {
            if (param.getValue() != null) {
                if (StringUtils.hasLength(String.valueOf(param.getCondition()))) {
                    if (param.getCondition().compareTo(OperatorEnum.OR) == 0) {
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

    protected Predicate matchesProperty(QueryParam param) {
        return Expressions.predicate(
                operators.get(param.getOperator()),
                param.getPath(),
                Expressions.constant(param.getValue())
        );
    }
}
