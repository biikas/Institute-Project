package com.nikosera.user.search;

import com.nikosera.entity.QUserGroup;
import com.nikosera.repository.querydsl.GenericSearch;
import com.nikosera.repository.querydsl.OperatorEnum;
import com.nikosera.repository.querydsl.utils.QueryParam;
import com.nikosera.user.request.UserGroupSearchRequest;
import com.google.common.collect.ImmutableList;
import com.querydsl.core.BooleanBuilder;
import org.springframework.stereotype.Repository;

/**
 * @author Narayan Joshi
 * @email narenzoshi@gmail.com
 */
@Repository
public class UserGroupSearch extends GenericSearch<UserGroupSearchRequest> {

    @Override
    public BooleanBuilder buildPredicate(UserGroupSearchRequest search) {

        QUserGroup userGroup = QUserGroup.userGroup;
        return buildWhereClause(ImmutableList.of(
                new QueryParam(search.getName(), userGroup.name, OperatorEnum.LIKE, OperatorEnum.AND),
                new QueryParam(search.getActive(), userGroup.active, OperatorEnum.EQUALS, OperatorEnum.AND)));
    }
}
