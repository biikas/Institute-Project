package com.f1soft.campaign.repository.custom;

import com.f1soft.campaign.repository.Util.FieldQueryParameter;
import com.querydsl.core.BooleanBuilder;

import java.util.List;

/**
 * @author Sabrin Luitel
 */
public interface ApplicationUserCustom {

    BooleanBuilder searchQuery(List<FieldQueryParameter> fieldQueryParameterList);

}
