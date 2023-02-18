package com.college.campaign.repository.Util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author <krishna.pandey@college.com>
 */
@Slf4j
@Component
public class QueryHelper {
    public Map<String, Object> convertToQueryMap(List<FieldQueryParameter> search) {
        return search.stream()
                .collect(Collectors.toMap(FieldQueryParameter::getKey, FieldQueryParameter::getValue));
    }
}
