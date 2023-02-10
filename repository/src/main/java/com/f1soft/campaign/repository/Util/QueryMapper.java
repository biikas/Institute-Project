package com.f1soft.campaign.repository.Util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/*
 * @Author Rashim Dhaubanjar
 */
@Slf4j
public class QueryMapper {

    private QueryMapper() {
    }

    public static List<SearchParameter> convertToSearchParameterList(List<FieldQueryParameter> paramList, Map<String, SearchField> searchMap) {
        List<SearchParameter> searchParameterList = new ArrayList<>();

        paramList.stream().map(param ->
                QueryMapper.convertToSearchParameter(param, searchMap)
        ).forEach(field -> {
            searchParameterList.add(field);
        });

        return searchParameterList;
    }

    public static List<SearchParameter> convertToStringList(List<FieldQueryParameter> paramList, Map<String, String> stringMap) {
        List<SearchParameter> searchParameterList = new ArrayList<>();

        paramList.stream()
                .filter(f -> !StringUtils.isEmpty(String.valueOf(f.getValue())))
                .map(param ->
                        QueryMapper.convertToStringParameter(param, stringMap)
                ).forEach(field -> {
            searchParameterList.add(field);
        });
        return searchParameterList;
    }

    private static SearchParameter convertToStringParameter(FieldQueryParameter param, Map<String, String> searchMap) {

        SearchParameter parameter = new SearchParameter();
        log.info("Param key : " + param.getKey());
        log.info("Param Value : " + param.getValue());

        parameter.setValue(param.getValue());
        parameter.setColumnName(searchMap.get(param.getKey()));
        return parameter;
    }


    private static SearchParameter convertToSearchParameter(FieldQueryParameter param, Map<String, SearchField> searchMap) {

        SearchParameter parameter = new SearchParameter();
        log.info("Param key : " + param.getKey());
        log.info("Param Value : " + param.getValue());
        log.info("Param Path : " + searchMap.get(param.getKey()).getPath());

        parameter.setValue(param.getValue());
        parameter.setPath(searchMap.get(param.getKey()).getPath());
        parameter.setCondition(searchMap.get(param.getKey()).getCondition());
        parameter.setOperator(searchMap.get(param.getKey()).getOperator());
        return parameter;
    }
}
