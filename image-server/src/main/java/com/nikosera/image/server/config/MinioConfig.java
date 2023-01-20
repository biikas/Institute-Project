package com.nikosera.image.server.config;

import io.minio.MinioClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author Narayan Joshi
 * @email narenzoshi@gmail.com
 */
@Slf4j
@Component
@Scope("singleton")
public class MinioConfig {
    protected MinioClient minioClient;

    protected MinioConfigurationProperties minioConfigurationProperties;

    public MinioConfig(MinioConfigurationProperties minioConfigurationProperties) {
        this.minioConfigurationProperties = minioConfigurationProperties;
    }

    @PostConstruct
    public void init() {
        minioClient = new MinioClient
                .Builder()
                .credentials(minioConfigurationProperties.getAccessKey(), minioConfigurationProperties.getSecretKey())
                .endpoint(minioConfigurationProperties.getUrl(), minioConfigurationProperties.getPort(), minioConfigurationProperties.isSecure())
                .build();
    }

}
