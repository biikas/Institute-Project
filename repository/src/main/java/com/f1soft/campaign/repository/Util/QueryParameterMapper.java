package com.f1soft.campaign.repository.Util;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Sabrin Luitel
 */
public class QueryParameterMapper {

    public static List<FieldQueryParameter> getQueryParameterListForFilter(String status) {
        List<FieldQueryParameter> fieldQueryParameterList = new ArrayList<>();
        fieldQueryParameterList.add(new FieldQueryParameter("status", status));
        return fieldQueryParameterList;
    }
}
