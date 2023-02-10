package com.f1soft.campaign.repository.custom;

import com.f1soft.campaign.repository.Util.FieldQueryParameter;
import com.querydsl.core.BooleanBuilder;

import java.util.List;

public interface CBSQueryCustom {

    BooleanBuilder searchCBSQuery(List<FieldQueryParameter> fieldQueryParameterList);
}
