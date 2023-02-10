package com.f1soft.campaign.repository.custom;

import com.f1soft.campaign.repository.Util.FieldQueryParameter;
import com.querydsl.core.BooleanBuilder;

import java.util.List;

/**
 * @author <krishna.pandey@f1soft.com>
 */
public interface GiftCardDetailCustom {

    BooleanBuilder searchQuery(List<FieldQueryParameter> fieldQueryParameterList);
}
