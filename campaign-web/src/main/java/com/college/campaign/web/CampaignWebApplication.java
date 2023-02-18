package com.college.campaign.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author Prajwol hada
 */
@Slf4j
@EnableScheduling
@EntityScan(basePackages = {"com.college"})
@EnableJpaRepositories(basePackages = {"com.college"})
@SpringBootApplication(scanBasePackages = {"com.college"})
public class CampaignWebApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {


        new SpringApplicationBuilder(CampaignWebApplication.class)
                .sources(CampaignWebApplication.class)
                .run(args);
    }


}
