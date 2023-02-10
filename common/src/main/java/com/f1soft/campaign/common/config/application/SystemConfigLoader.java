package com.f1soft.campaign.common.config.application;

import com.f1soft.campaign.entities.model.SystemConfigMaster;
import com.f1soft.campaign.repository.SystemConfigMasterRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Component
public class SystemConfigLoader {

    @Autowired
    private SystemConfigMasterRepository systemConfigMasterRepository;

    private static final String ADMIN_CONFIG_CODE = "ADMIN_CONFIG";
    private static final String ADMIN_PASSWORD_CODE = "ADMIN_PASSWORD";
    private static final String MINIO = "MINIO";
    private static final String APP_CONFIG_CODE = "APP_CONFIG";
    private static final String RUNNER_CONFIG_CODE = "RUNNER";
    private static final String NOTIFICATION_CODE = "NOTIFICATION";

    private Map<String, String> convertToServiceMap(SystemConfigMaster systemConfigMaster) {
        return systemConfigMaster.getSystemConfigs()
                .stream().filter(f -> f.getActive() == 'Y')
                .collect(Collectors.toMap(com.f1soft.campaign.entities.model.SystemConfig::getParamCode, com.f1soft.campaign.entities.model.SystemConfig::getParamValue));
    }

    private SystemConfigMaster getServiceCredential(String code) {
        return systemConfigMasterRepository.getSystemConfigByCode(code);
    }

    public Map<String, String> getAdminConfig() {
        return convertToServiceMap(getServiceCredential(ADMIN_CONFIG_CODE));
    }

    public Map<String, String> getAdminPasswordConfig() {
        return convertToServiceMap(getServiceCredential(ADMIN_PASSWORD_CODE));
    }

    public Map<String, String> getMinioConfig() {
        return convertToServiceMap(getServiceCredential(MINIO));
    }

    public Map<String, String> getAppConfig() {
        return convertToServiceMap(getServiceCredential(APP_CONFIG_CODE));
    }

    public Map<String, String> getRunnerConfig() {
        return convertToServiceMap(getServiceCredential(RUNNER_CONFIG_CODE));
    }

    public Map<String, String> getNotificationConfig() {
        return convertToServiceMap(getServiceCredential(NOTIFICATION_CODE));
    }
}
