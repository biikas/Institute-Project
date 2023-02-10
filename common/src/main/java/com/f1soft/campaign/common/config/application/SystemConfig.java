package com.f1soft.campaign.common.config.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SystemConfig {

    @Autowired
    private SystemConfigLoader systemConfigLoader;

    public String adminConfig(String key) {
        return systemConfigLoader.getAdminConfig().get(key);
    }

    public String adminPasswordConfig(String key) {
        return systemConfigLoader.getAdminPasswordConfig().get(key);
    }

    public String minioConfig(String key) {
        return systemConfigLoader.getMinioConfig().get(key);
    }

    public String appConfig(String key) {
        return systemConfigLoader.getAppConfig().get(key);
    }

    public String runnerConfig(String key) {
        return systemConfigLoader.getRunnerConfig().get(key);
    }

    public String notificationConfig(String key) {
        return systemConfigLoader.getNotificationConfig().get(key);
    }
}
