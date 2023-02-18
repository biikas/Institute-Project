package com.college.campaign.common.helper;

import com.college.campaign.repository.Util.FieldQueryParameter;
import com.college.campaign.repository.Util.SearchQueryParameter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author <krishna.pandey@college.com>
 */
@Slf4j
@Component
public class QueryFilterHelper {

    public SearchQueryParameter modifySearchQueryParam(SearchQueryParameter searchQueryParameter) {

        List<FieldQueryParameter> search = new ArrayList<>();
        for (FieldQueryParameter fieldQueryParameter : searchQueryParameter.getSearch()) {

            if (fieldQueryParameter.getKey().equalsIgnoreCase("giftCardProviderId")) {
                if (fieldQueryParameter.getValue().toString().length() > 0) {
                    FieldQueryParameter parameter = new FieldQueryParameter();
                    parameter.setKey("giftCardProvider");
                    parameter.setValue(Long.valueOf(fieldQueryParameter.getValue().toString()));
                    search.add(parameter);
                }
            } else if (fieldQueryParameter.getKey().equalsIgnoreCase("active") && fieldQueryParameter.getValue().toString().length() > 0) {
                FieldQueryParameter parameter = new FieldQueryParameter();
                parameter.setKey("active");
                parameter.setValue((fieldQueryParameter.getValue().toString()).charAt(0));
                search.add(parameter);
            } else {
                search.add(fieldQueryParameter);
            }
        }
        searchQueryParameter.setSearch(search);
        return searchQueryParameter;
    }
}

