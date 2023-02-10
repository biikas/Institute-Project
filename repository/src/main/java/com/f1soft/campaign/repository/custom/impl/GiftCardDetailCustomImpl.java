package com.f1soft.campaign.repository.custom.impl;

import com.f1soft.campaign.repository.Util.FieldQueryParameter;
import com.f1soft.campaign.repository.Util.QueryMapper;
import com.f1soft.campaign.repository.Util.SearchField;
import com.f1soft.campaign.repository.Util.SearchParameter;
import com.f1soft.campaign.repository.custom.GiftCardDetailCustom;
import com.f1soft.campaign.repository.querydsl.AbstractQueryDslBuilder;
import com.google.common.collect.ImmutableMap;
import com.querydsl.core.BooleanBuilder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @author <krishna.pandey@f1soft.com>
 */
@Repository
@Transactional
public class GiftCardDetailCustomImpl extends AbstractQueryDslBuilder implements GiftCardDetailCustom {

    protected Map<String, SearchField> search = ImmutableMap.<String, SearchField>builder()
            .put("name", new SearchField(giftCard.name, "::", "&&"))
            .put("code", new SearchField(giftCard.code, "::", "&&"))
            .put("active", new SearchField(giftCard.active, "==", "&&"))
            .put("giftCardProvider", new SearchField(giftCard.giftCardProvider.id, "==", "&&"))
            .build();

    @Override
    public BooleanBuilder searchQuery(List<FieldQueryParameter> fieldQueryParameterList) {
        List<SearchParameter> searchParameterList = QueryMapper.convertToSearchParameterList(fieldQueryParameterList, search);
        BooleanBuilder booleanBuilder = new BooleanBuilder();
//        booleanBuilder.and(campaign.active.eq('Y'));
        return buildWhereClause(searchParameterList, booleanBuilder);
    }
}
