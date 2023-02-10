package com.f1soft.campaign.web.config;

import com.f1soft.campaign.repository.ClientRepository;
import com.f1soft.campaign.web.config.jwt.TokenAuthorizationFilter;
import com.f1soft.campaign.web.hmac.HmacAccessFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.*;
import org.springframework.security.web.header.writers.StaticHeadersWriter;

import static com.f1soft.campaign.web.config.RestPath.JWT_URLS;
import static org.springframework.http.HttpStatus.FORBIDDEN;

/*
 * @Author Rashim Dhaubanjar
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                // Custom JWT based security filter
                .exceptionHandling()
                .and()
                .cors()
                .and()
                .csrf()
                .disable()
                .headers()
                .addHeaderWriter(new StaticHeadersWriter("X-Content-Security-Policy", "script-src 'self'"))
                .frameOptions()
                .disable()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilterBefore(tokenAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(hmacAccessFilter(), UsernamePasswordAuthenticationFilter.class);

    }


    @Autowired
    private ClientRepository clientRepository;

    TokenAuthorizationFilter tokenAuthorizationFilter() throws Exception {
        final TokenAuthorizationFilter filter = new TokenAuthorizationFilter(JWT_URLS);
        filter.setAuthenticationManager(authenticationManager());
        filter.setAuthenticationSuccessHandler(successHandler());
        filter.setAuthenticationFailureHandler(failureHandler());
        return filter;
    }

    private HmacAccessFilter hmacAccessFilter() throws Exception {
        final HmacAccessFilter filter = new HmacAccessFilter(RestPath.HMAC_URLS, clientRepository);
        filter.setAuthenticationManager(authenticationManager());
        filter.setAuthenticationSuccessHandler(successHandler());
        filter.setAuthenticationFailureHandler(failureHandler());
        return filter;
    }

    @Bean
    SimpleUrlAuthenticationSuccessHandler successHandler() {
        final SimpleUrlAuthenticationSuccessHandler successHandler = new SimpleUrlAuthenticationSuccessHandler();
        successHandler.setRedirectStrategy(new NoRedirectStrategy());
        return successHandler;
    }

    @Bean
    AuthenticationEntryPoint forbiddenEntryPoint() {
        return new HttpStatusEntryPoint(FORBIDDEN);
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    AuthenticationFailureHandler failureHandler() {
        final AuthenticationFailureHandler failureHandler = new AuthenticationEntryPointFailureHandler(authenticationEntryPoint());
        return failureHandler;
    }

    @Bean
    AuthenticationEntryPoint authenticationEntryPoint() {
        return new TokenAuthenticationEntryPoint();
    }

}
