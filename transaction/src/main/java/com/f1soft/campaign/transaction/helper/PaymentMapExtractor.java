package com.f1soft.campaign.transaction.helper;

import com.f1soft.campaign.entities.model.DefaultIsoParam;
import com.f1soft.campaign.repository.DefaultIsoParamRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Component
@SuppressWarnings("Duplicates")
public class PaymentMapExtractor {

    @Autowired
    private DefaultIsoParamRepository defaultIsoParamRepository;


    public Map<String, String> fetchIsoParam(String type) {
        Map<String, String> defaultIsoParamMap = new HashMap<>();
        List<DefaultIsoParam> defaultIsoParams = defaultIsoParamRepository.getDefaultIsoParamByType(type);

        defaultIsoParamMap = defaultIsoParams
                .stream()
                .filter(param -> param.getActive() == 'Y')
                .map(param -> {
                    param.getIsoField().trim();
                    param.getParameter().trim();
                    return param;
                })
                .collect(Collectors.toMap(DefaultIsoParam::getIsoField, DefaultIsoParam::getParameter));

        return defaultIsoParamMap;
    }

}
