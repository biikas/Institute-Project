package com.f1soft.campaign.web.crud.systemconfigmaster;

import com.f1soft.campaign.common.util.DateFormatter;
import com.f1soft.campaign.entities.model.SystemConfigMaster;

import java.util.Date;


public class SystemConfigMasterMapper {

    public static SystemConfigMaster convertToCreateSystemConfigMaster(SystemConfigMasterRequest systemConfigMasterRequest) {
        SystemConfigMaster systemConfigMaster = new SystemConfigMaster();
        systemConfigMaster.setActive('Y');
        systemConfigMaster.setCreatedDate(new Date());
        systemConfigMaster.setCode(systemConfigMasterRequest.getCode());
        systemConfigMaster.setName(systemConfigMasterRequest.getName());
        return systemConfigMaster;
    }

    public static SystemConfigMasterDTO convertToSystemConfigmasterDTO(SystemConfigMaster systemConfigMaster) {
        SystemConfigMasterDTO systemConfigMasterDTO = new SystemConfigMasterDTO();
        systemConfigMasterDTO.setId(systemConfigMaster.getId());
        systemConfigMasterDTO.setCreatedDate(DateFormatter.convertToString(systemConfigMaster.getCreatedDate()));
        systemConfigMasterDTO.setCode(systemConfigMaster.getCode());
        systemConfigMasterDTO.setName(systemConfigMaster.getName());
        systemConfigMasterDTO.setActive(systemConfigMaster.getActive());
        return systemConfigMasterDTO;
    }

    public static SystemConfigMaster convertToModifySystemConfigMaster(SystemConfigMaster systemConfigMaster, SystemConfigMasterRequest systemConfigMasterRequest) {
        systemConfigMaster.setName(systemConfigMasterRequest.getName());
        systemConfigMaster.setCode(systemConfigMasterRequest.getCode());
        systemConfigMaster.setLastModifiedDate(new Date());
        systemConfigMaster.setActive(systemConfigMasterRequest.getActive());
        return systemConfigMaster;
    }
}
