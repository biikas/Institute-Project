package com.nikosera.image.server.config;

import com.nikosera.common.util.YamlPropertySourceFactory;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author Narayan Joshi
 * @email narenzoshi@gmail.com
 */
@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "minio")
@PropertySource(factory = YamlPropertySourceFactory.class, value = "classpath:minio.yml")
public class MinioConfigurationProperties {

    /**
     * URL for Minio instance. Can include the HTTP scheme.
     */
    private String url;

    /**
     * Must include the port. If the port is not provided, then the port of the HTTP is taken.
     */
    private int port;

    /**
     * Access key (login) on Minio instance
     */
    private String accessKey;

    /**
     * Secret key (password) on Minio instance
     */
    private String secretKey;

    /**
     * If the scheme is not provided in {@code url} property, define if the connection is done via HTTP or HTTPS.
     */
    private boolean secure;
}