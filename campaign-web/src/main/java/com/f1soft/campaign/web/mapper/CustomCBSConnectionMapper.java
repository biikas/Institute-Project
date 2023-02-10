package com.f1soft.campaign.web.mapper;

import com.f1soft.campaign.common.util.DateFormatter;
import com.f1soft.campaign.entities.model.CustomCBSConnection;
import com.f1soft.campaign.web.config.provider.LoginProvider;
import com.f1soft.campaign.web.dto.CustomCBSConnectionDTO;
import com.f1soft.campaign.web.dto.request.CustomCBSConnectionRequest;
import com.f1soft.campaign.web.dto.request.TestCBSConnectionRequest;

import java.util.Date;

public class CustomCBSConnectionMapper {

    public static CustomCBSConnection convertToCreateCustomCBSConnection(CustomCBSConnectionRequest customCBSConnectionRequest) {
        CustomCBSConnection customCBSConnection = new CustomCBSConnection();
        customCBSConnection.setCbsConnectionURL(customCBSConnectionRequest.getCbsConnectionURL());
        customCBSConnection.setCbsDriverName(customCBSConnectionRequest.getCbsDriverName());
        customCBSConnection.setActive('Y');
        customCBSConnection.setCbsUsername(customCBSConnectionRequest.getCbsUsername());
        customCBSConnection.setCbsPassword(customCBSConnectionRequest.getCbsPassword());
        customCBSConnection.setCreatedBy(LoginProvider.getApplicationUser());
        customCBSConnection.setCreatedDate(new Date());

        return customCBSConnection;
    }

    public static CustomCBSConnection convertToModifyCustomCBSConnection(CustomCBSConnection customCBSConnection, CustomCBSConnectionRequest cbsConnectionRequest) {
        customCBSConnection.setCbsConnectionURL(cbsConnectionRequest.getCbsConnectionURL());
        customCBSConnection.setCbsDriverName(cbsConnectionRequest.getCbsDriverName());
        customCBSConnection.setActive(cbsConnectionRequest.getActive());
        customCBSConnection.setCbsUsername(cbsConnectionRequest.getCbsUsername());
        customCBSConnection.setCbsPassword(cbsConnectionRequest.getCbsPassword());
        customCBSConnection.setModifiedBy(LoginProvider.getApplicationUser());
        customCBSConnection.setModifiedDate(new Date());
        return customCBSConnection;
    }

    public static CustomCBSConnectionDTO convertToCustomCBSConnection(CustomCBSConnection cbsConnection) {

        CustomCBSConnectionDTO cbsConnectionDTO = new CustomCBSConnectionDTO();
        cbsConnectionDTO.setId(cbsConnection.getId());
        cbsConnectionDTO.setCbsConnectionURL(cbsConnection.getCbsConnectionURL());
        cbsConnectionDTO.setActive(cbsConnection.getActive());
        cbsConnectionDTO.setCbsPassword(cbsConnection.getCbsPassword());
        cbsConnectionDTO.setCbsUsername(cbsConnection.getCbsUsername());
        cbsConnectionDTO.setCbsDriverName(cbsConnection.getCbsDriverName());

        if (cbsConnection.getCreatedBy() != null) {
            cbsConnectionDTO.setCreatedBy(cbsConnection.getCreatedBy().getUsername());
        }
        if (cbsConnection.getCreatedDate() != null) {
            cbsConnectionDTO.setCreatedDate(DateFormatter.convertToString(cbsConnection.getCreatedDate()));
        }
        if (cbsConnection.getModifiedBy() != null) {
            cbsConnectionDTO.setModifiedBy(cbsConnection.getModifiedBy().getUsername());
        }
        if (cbsConnection.getModifiedDate() != null) {
            cbsConnectionDTO.setModifiedDate(DateFormatter.convertToString(cbsConnection.getModifiedDate()));
        }

        return cbsConnectionDTO;
    }


    public static TestCBSConnectionRequest convertToTestCBSConnection(CustomCBSConnection cbsConnection) {
        TestCBSConnectionRequest testCBSConnectionRequest = new TestCBSConnectionRequest();
        testCBSConnectionRequest.setCbsConnectionURL(cbsConnection.getCbsConnectionURL());
        testCBSConnectionRequest.setCbsDriverName(cbsConnection.getCbsDriverName());
        testCBSConnectionRequest.setCbsUsername(cbsConnection.getCbsUsername());
        testCBSConnectionRequest.setCbsPassword(cbsConnection.getCbsPassword());
        return testCBSConnectionRequest;
    }

}
