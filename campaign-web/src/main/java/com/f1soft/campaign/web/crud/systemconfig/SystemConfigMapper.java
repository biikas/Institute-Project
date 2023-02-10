package com.f1soft.campaign.web.crud.systemconfig;


import com.f1soft.campaign.common.util.DateFormatter;
import com.f1soft.campaign.entities.model.SystemConfig;
import com.f1soft.campaign.entities.model.SystemConfigMaster;
import com.f1soft.campaign.web.config.provider.LoginProvider;

import java.util.Date;

public class SystemConfigMapper {

    public static SystemConfig convertToCreateSystemConfig(SystemConfigRequest systemConfigRequest, SystemConfigMaster systemConfigMaster) {
        SystemConfig systemConfig = new SystemConfig();
        systemConfig.setActive('Y');
        systemConfig.setCreatedDate(new Date());
        systemConfig.setParamCode(systemConfigRequest.getParamCode());
        systemConfig.setParamValue(systemConfigRequest.getParamValue());
        systemConfig.setAllowedValue(systemConfigRequest.getAllowedValue());
        systemConfig.setSystemConfigMaster(systemConfigMaster);
        systemConfig.setCreatedBy(LoginProvider.getApplicationUser());
        return systemConfig;
    }

    public static SystemConfigDTO convertToSystemConfigDTO(SystemConfig systemConfig) {
        SystemConfigDTO systemConfigDTO = new SystemConfigDTO();
        systemConfigDTO.setId(Long.valueOf(systemConfig.getId()));
        systemConfigDTO.setCreatedDate(DateFormatter.convertToString(systemConfig.getCreatedDate()));
        systemConfigDTO.setParamCode(systemConfig.getParamCode());
        systemConfigDTO.setParamValue(systemConfig.getParamValue());
        systemConfigDTO.setAllowedValue(systemConfig.getAllowedValue());
        systemConfigDTO.setSystemConfigMasterId(systemConfig.getSystemConfigMaster().getId());
        systemConfigDTO.setActive(systemConfig.getActive());
        return systemConfigDTO;
    }

    public static SystemConfig convertToModifySystemConfig(SystemConfig systemConfig, SystemConfigRequest systemConfigRequest, SystemConfigMaster systemConfigMaster) {
        systemConfig.setParamCode(systemConfigRequest.getParamCode());
        systemConfig.setParamValue(systemConfigRequest.getParamValue());
        systemConfig.setSystemConfigMaster(systemConfigMaster);
        systemConfig.setAllowedValue(systemConfigRequest.getAllowedValue());
        systemConfig.setLastModifiedDate(new Date());
        systemConfig.setLastModifiedBy(LoginProvider.getApplicationUser());
        systemConfig.setActive(systemConfigRequest.getActive());
        return systemConfig;
    }

}
