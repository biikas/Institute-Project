package com.nikosera.user.search;

import com.nikosera.entity.QApplicationUser;
import com.nikosera.repository.querydsl.GenericSearch;
import com.nikosera.repository.querydsl.OperatorEnum;
import com.nikosera.repository.querydsl.utils.QueryParam;
import com.nikosera.user.request.UserSearchRequest;
import com.google.common.collect.ImmutableList;
import com.querydsl.core.BooleanBuilder;
import org.springframework.stereotype.Repository;

/**
 * @author Narayan Joshi
 * @email narenzoshi@gmail.com
 */
@Repository
public class UserSearch extends GenericSearch<UserSearchRequest> {

    @Override
    public BooleanBuilder buildPredicate(UserSearchRequest search) {

        QApplicationUser user = QApplicationUser.applicationUser;
        return buildWhereClause(ImmutableList.of(
                new QueryParam(search.getName(), user.name, OperatorEnum.LIKE, OperatorEnum.AND),
                new QueryParam(search.getUsername(), user.username, OperatorEnum.LIKE, OperatorEnum.AND),
                new QueryParam(search.getActive(), user.active, OperatorEnum.EQUALS, OperatorEnum.AND)));
    }
}
