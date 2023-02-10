package com.f1soft.campaign.repository.custom.impl;

import com.f1soft.campaign.repository.Util.FieldQueryParameter;
import com.f1soft.campaign.repository.Util.QueryMapper;
import com.f1soft.campaign.repository.Util.SearchField;
import com.f1soft.campaign.repository.Util.SearchParameter;
import com.f1soft.campaign.repository.custom.ApplicationUserCustom;
import com.f1soft.campaign.repository.custom.CampaignCustom;
import com.f1soft.campaign.repository.querydsl.AbstractQueryDslBuilder;
import com.google.common.collect.ImmutableMap;
import com.querydsl.core.BooleanBuilder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Map;

/**
 * @author Rashim Dhaubanjar
 */
@Repository
@Transactional
public class ApplicationUserRepositoryImpl extends AbstractQueryDslBuilder implements ApplicationUserCustom {

    @PersistenceContext
    private EntityManager em;

    protected Map<String, SearchField> search = ImmutableMap.<String, SearchField>builder()
            .put("name", new SearchField(applicationUser.name, "::", "&&"))
            .put("username", new SearchField(applicationUser.username, "::", "&&"))
            .build();

    @Override
    public BooleanBuilder searchQuery(List<FieldQueryParameter> fieldQueryParameterList) {
        List<SearchParameter> searchParameterList = QueryMapper.convertToSearchParameterList(fieldQueryParameterList, search);
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        return buildWhereClause(searchParameterList, booleanBuilder);
    }
}
