package com.f1soft.campaign.transaction.builder;

import com.f1soft.campaign.transaction.dto.IsoDescriptionParameter;
import com.f1soft.campaign.transaction.constant.IsoParameterConstant;

import java.util.HashMap;
import java.util.Map;

public class IsoMapBuilder {
    /*
   isoFieldMap : map value from default_iso_param table
   paramMap: merchant payment parameters map
   customParameterMap: map generated using customer parameters
    */
    public Map<String, String> build(Map<String, String> isoNarrationMap, Map<String, String> paramMap, IsoDescriptionParameter isoDescriptionParameter) {
        Map<String, String> convertedIsoNarrationMap = new HashMap<>();

        Map<String, String> customParameterMap = buildCustomParamaterMap(isoDescriptionParameter);

        Map<String, String> combinedParameterMap = new HashMap<>();
        combinedParameterMap.putAll(paramMap);
        combinedParameterMap.putAll(customParameterMap);

        isoNarrationMap.entrySet()
                .stream()
                .forEach(entry -> {
                    String isoField = entry.getKey();
                    String parameter = entry.getValue();
                    String convertedParameterValue = replace(parameter, combinedParameterMap);
                    convertedIsoNarrationMap.put(isoField, convertedParameterValue);
                });
        return convertedIsoNarrationMap;
    }

    private String replace(String parameter, Map<String, String> parameterMap) {
        for (Map.Entry<String, String> entry : parameterMap.entrySet()) {
            String token = entry.getKey();
            String value = entry.getValue();

            String convertedParameter = parameter.replace(token, value);
            parameter = convertedParameter;
        }
        return parameter;
    }

    public Map<String, String> buildCustomParamaterMap(IsoDescriptionParameter isoDescriptionParameter) {
        Map<String, String> customParameterMap = new HashMap<>();
        customParameterMap.put(IsoParameterConstant.TRACE_ID, isoDescriptionParameter.getTraceId());
        customParameterMap.put(IsoParameterConstant.SERVICE_NAME, isoDescriptionParameter.getServiceName());
        customParameterMap.put(IsoParameterConstant.SERVICE_CODE, isoDescriptionParameter.getServiceCode());
        customParameterMap.put(IsoParameterConstant.INITIATOR, isoDescriptionParameter.getInitiator());
        customParameterMap.put(IsoParameterConstant.PAYMENT_ATTRIBUTE, isoDescriptionParameter.getPaymentAttribute());
        customParameterMap.put(IsoParameterConstant.REMARKS, isoDescriptionParameter.getRemarks());
        customParameterMap.put(IsoParameterConstant.ORIGINATOR_UNIQUE_CODE, isoDescriptionParameter.getOriginatorUniqueCode());
        customParameterMap.put(IsoParameterConstant.FROM_ACCOUNT, isoDescriptionParameter.getFromAccount());
        customParameterMap.put(IsoParameterConstant.TO_ACCOUNT, isoDescriptionParameter.getToAccount());
        customParameterMap.put(IsoParameterConstant.TELLER_USER_ID, isoDescriptionParameter.getTellerUserId());
        customParameterMap.put(IsoParameterConstant.REQUEST_CHANNEL, isoDescriptionParameter.getRequestChannel());
        customParameterMap.put(IsoParameterConstant.TERMINAL_ID, isoDescriptionParameter.getRequestChannel());
        customParameterMap.put(IsoParameterConstant.REVERSAL_ID, isoDescriptionParameter.getReversalId());
        customParameterMap.put(IsoParameterConstant.PROMO_CODE, isoDescriptionParameter.getPromoCode());
        return customParameterMap;
    }
}
