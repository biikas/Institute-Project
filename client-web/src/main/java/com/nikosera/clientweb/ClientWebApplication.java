package com.nikosera.clientweb;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {"com.nikosera"})
@EnableJpaRepositories(basePackages = {"com.nikosera"})
@EntityScan(basePackages = {"com.nikosera"})
public class ClientWebApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        new SpringApplicationBuilder(ClientWebApplication.class)
                .sources(ClientWebApplication.class)
                .run(args);
    }
}

