package com.nikosera.cas.config;


import com.nikosera.cas.audit.AuditAwareImpl;
import com.nikosera.entity.ApplicationUser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class JpaConfig {
    @Bean
    public AuditorAware<ApplicationUser> auditorAware() {
        return new AuditAwareImpl();
    }

}

