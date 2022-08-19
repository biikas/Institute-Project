package com.sms.bli;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(scanBasePackages = {"com.sms.bli"},exclude = {SecurityAutoConfiguration.class })
public class BliApplication {

	public static void main(String[] args) {
		SpringApplication.run(BliApplication.class, args);
	}

}


