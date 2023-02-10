package com.f1soft.campaign.web.mapper;

import com.f1soft.campaign.common.util.DateFormatter;
import com.f1soft.campaign.entities.model.CustomCBSConnection;
import com.f1soft.campaign.entities.model.CustomCBSQuery;
import com.f1soft.campaign.web.config.provider.LoginProvider;
import com.f1soft.campaign.web.dto.CustomCBSConnectionDTO;
import com.f1soft.campaign.web.dto.CustomCbsQueryDTO;
import com.f1soft.campaign.web.dto.request.CustomCbsQueryRequest;

import java.util.Date;

/**
 * @author Shreetika Panta
 */
public class CustomCbsQueryMapper {
    public static CustomCbsQueryDTO convertToCustomCbsQueryDTO(CustomCBSQuery customCBSQuery) {

        CustomCbsQueryDTO cbsQueryDTO = new CustomCbsQueryDTO();
        cbsQueryDTO.setId(customCBSQuery.getId());
        cbsQueryDTO.setActive(customCBSQuery.getActive());
        cbsQueryDTO.setQueryCode(customCBSQuery.getQueryCode());
        cbsQueryDTO.setCbsConnectionId(customCBSQuery.getCustomCbsConnection().getId());
        cbsQueryDTO.setQueryDescription(customCBSQuery.getQueryDescription());
        cbsQueryDTO.setSqlQuery(customCBSQuery.getSqlQuery());
        cbsQueryDTO.setCbsConnectionURL(customCBSQuery.getCustomCbsConnection().getCbsConnectionURL());
        if (customCBSQuery.getCreatedBy() != null) {
            cbsQueryDTO.setCreatedBy(customCBSQuery.getCreatedBy().getUsername());
        }
        if (customCBSQuery.getCreatedDate() != null) {
            cbsQueryDTO.setCreatedDate(DateFormatter.convertToString(customCBSQuery.getCreatedDate()));
        }
        if (customCBSQuery.getModifiedBy() != null) {
            cbsQueryDTO.setModifiedBy(customCBSQuery.getModifiedBy().getUsername());
        }
        if (customCBSQuery.getCreatedDate() != null) {
            cbsQueryDTO.setModifiedDate(DateFormatter.convertToString(customCBSQuery.getModifiedDate()));
        }
        if (customCBSQuery.getCbsDstribution() != null) {
            cbsQueryDTO.setCbsDistribution(customCBSQuery.getCbsDstribution());

        }
        return cbsQueryDTO;
    }

    public static CustomCBSQuery convertToCreateCustomCBSQuery(CustomCbsQueryRequest cbsQueryRequest, CustomCBSConnection customCBSConnection) {
        CustomCBSQuery customCBSQuery = new CustomCBSQuery();
        customCBSQuery.setActive('Y');
        customCBSQuery.setCbsDstribution(cbsQueryRequest.getCbsDistribution());
        customCBSQuery.setQueryCode(cbsQueryRequest.getQueryCode());
        customCBSQuery.setCustomCbsConnection(customCBSConnection);
        customCBSQuery.setSqlQuery(cbsQueryRequest.getSqlQuery());
        customCBSQuery.setCreatedBy(LoginProvider.getApplicationUser());
        customCBSQuery.setCreatedDate(new Date());
        customCBSQuery.setQueryDescription(cbsQueryRequest.getQueryDescription());
        return customCBSQuery;
    }

    public static CustomCBSQuery convertToModifyCustomCBSQuery(CustomCBSQuery customCBSQuery, CustomCbsQueryRequest cbsQueryRequest, CustomCBSConnection customCBSConnection) {

        customCBSQuery.setActive(cbsQueryRequest.getActive());
        customCBSQuery.setCbsDstribution(cbsQueryRequest.getCbsDistribution());
        customCBSQuery.setSqlQuery(cbsQueryRequest.getSqlQuery());
        customCBSQuery.setQueryCode(customCBSQuery.getQueryCode());
        customCBSQuery.setQueryDescription(cbsQueryRequest.getQueryDescription());
        customCBSQuery.setModifiedBy(LoginProvider.getApplicationUser());
        customCBSQuery.setModifiedDate(new Date());
        customCBSQuery.setCustomCbsConnection(customCBSConnection);
        return customCBSQuery;
    }

}
