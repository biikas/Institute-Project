package com.f1soft.bankxp.minio.client.configLoader;

import com.f1soft.campaign.entities.model.SystemConfig;
import com.f1soft.campaign.entities.model.SystemConfigMaster;
import com.f1soft.campaign.repository.SystemConfigMasterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.stream.Collectors;

@Component
public class MinioClientConfigLoader {

    @Autowired
    private SystemConfigMasterRepository systemConfigMasterRepository;

    private static final String MINIO_CODE = "MINIO";

    private Map<String, String> convertToServiceMap(SystemConfigMaster systemConfigMaster) {
        return systemConfigMaster.getSystemConfigs()
                .stream().filter(f -> f.getActive() == 'Y')
                .collect(Collectors.toMap(SystemConfig::getParamCode, SystemConfig::getParamValue));
    }

    private SystemConfigMaster getServiceCredential(String code) {
        return systemConfigMasterRepository.getSystemConfigByCode(code);
    }

    public Map<String, String> getMinioConfig() {
        return convertToServiceMap(getServiceCredential(MINIO_CODE));
    }

    public String minioConfig(String key) {
        return getMinioConfig().get(key);
    }

}
