package com.college.campaign.repository.custom;

import com.college.campaign.repository.Util.FieldQueryParameter;
import com.querydsl.core.BooleanBuilder;

import java.util.List;

/**
 * @author Sabrin Luitel
 */
public interface ApplicationUserCustom {

    BooleanBuilder searchQuery(List<FieldQueryParameter> fieldQueryParameterList);

}
