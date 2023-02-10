package com.f1soft.campaign.repository.custom.impl;

import com.f1soft.campaign.entities.model.QCustomCBSQuery;
import com.f1soft.campaign.repository.Util.FieldQueryParameter;
import com.f1soft.campaign.repository.Util.QueryMapper;
import com.f1soft.campaign.repository.Util.SearchField;
import com.f1soft.campaign.repository.Util.SearchParameter;
import com.f1soft.campaign.repository.custom.CBSQueryCustom;
import com.f1soft.campaign.repository.querydsl.AbstractQueryDslBuilder;
import com.google.common.collect.ImmutableMap;
import com.querydsl.core.BooleanBuilder;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;


@Repository
@Transactional
public class CustomCbsQueryRepositoryImpl extends AbstractQueryDslBuilder implements CBSQueryCustom {

    QCustomCBSQuery customCBSQuery = QCustomCBSQuery.customCBSQuery;
    protected Map<String, SearchField> searchCBSQuery = ImmutableMap.<String, SearchField>builder()
            .put("queryCode", new SearchField(customCBSQuery.queryCode, "::", "&&"))
            .build();
    @PersistenceContext
    private EntityManager em;

    @Override
    public BooleanBuilder searchCBSQuery(List<FieldQueryParameter> fieldQueryParameterList) {
        List<SearchParameter> searchParameterList = QueryMapper.convertToSearchParameterList(fieldQueryParameterList, searchCBSQuery);
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        return buildWhereClause(searchParameterList, booleanBuilder);
    }
}
