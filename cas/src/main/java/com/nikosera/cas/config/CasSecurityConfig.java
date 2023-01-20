package com.nikosera.cas.config;

import com.nikosera.cas.filter.authentication.FullyAuthenticatedTokenAuthorizationFilter;
import com.nikosera.cas.filter.authentication.PartiallyAuthenticatedTokenAuthorizationFilter;
import com.nikosera.cas.filter.authentication.exception.handler.TokenAuthenticationEntryPoint;
import com.nikosera.cas.oauth2.CustomOAuth2UserService;
import com.nikosera.cas.oauth2.HttpCookieOAuth2AuthorizationRequestRepository;
import com.nikosera.cas.oauth2.OAuth2AuthenticationFailureHandler;
import com.nikosera.cas.oauth2.OAuth2AuthenticationSuccessHandler;
import com.nikosera.cas.provider.TokenBasedFullyAuthenticatedProvider;
import com.nikosera.cas.provider.TokenBasedPartialAuthenticatedProvider;
import com.nikosera.cas.provider.UsernamePasswordBasedAuthProvider;
import com.nikosera.cas.restpath.RestPath;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.AuthenticationEntryPointFailureHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * @author Narayan Joshi
 * @email narenzoshi@gmail.com
 */
@Slf4j
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class CasSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomOAuth2UserService customOAuth2UserService;

    @Autowired
    private OAuth2AuthenticationSuccessHandler oAuth2AuthenticationSuccessHandler;

    @Autowired
    private OAuth2AuthenticationFailureHandler oAuth2AuthenticationFailureHandler;

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
                .xssProtection()
                .and()
                .frameOptions()
                .disable()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/",
                        "/error",
                        "/favicon.ico",
                        "/**/*.png",
                        "/**/*.gif",
                        "/**/*.svg",
                        "/**/*.jpg",
                        "/**/*.html",
                        "/**/*.css",
                        "/**/*.js")
                .permitAll()
                .antMatchers("/auth/**", "/oauth2/**")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .oauth2Login()
                .authorizationEndpoint()
                .baseUri("/oauth2/authorize")
                .authorizationRequestRepository(cookieAuthorizationRequestRepository())
                .and()
                .redirectionEndpoint()
                .baseUri("/oauth2/callback/*")
                .and()
                .userInfoEndpoint()
                .userService(customOAuth2UserService)
                .and()
                .successHandler(oAuth2AuthenticationSuccessHandler)
                .failureHandler(oAuth2AuthenticationFailureHandler)
                .and()
                .addFilterAfter(otpAuthTokenFilter(), UsernamePasswordAuthenticationFilter.class)
                .addFilterAfter(authenticatedAuthTokenFilter(), UsernamePasswordAuthenticationFilter.class)
                .headers().cacheControl();
    }

    @Bean
    FullyAuthenticatedTokenAuthorizationFilter authenticatedAuthTokenFilter() throws Exception {
        final FullyAuthenticatedTokenAuthorizationFilter filter = new FullyAuthenticatedTokenAuthorizationFilter(RestPath.JWT_URLS);
        filter.setAuthenticationManager(authenticationManager());
        filter.setAuthenticationSuccessHandler(successHandler());
        filter.setAuthenticationFailureHandler(failureHandler());
        return filter;
    }

    @Bean
    PartiallyAuthenticatedTokenAuthorizationFilter otpAuthTokenFilter() throws Exception {
        final PartiallyAuthenticatedTokenAuthorizationFilter filter = new PartiallyAuthenticatedTokenAuthorizationFilter(new AntPathRequestMatcher("/validate/otp"));
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
    AuthenticationFailureHandler failureHandler() {
        return new AuthenticationEntryPointFailureHandler(authenticationEntryPoint());
    }

    @Bean
    AuthenticationEntryPoint authenticationEntryPoint() {
        return new TokenAuthenticationEntryPoint();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(usernamePasswordBasedAuthProvider());
        auth.authenticationProvider(tokenBasedPartialAuthenticatedProvider());
        auth.authenticationProvider(tokenBasedFullyAuthenticatedProvider());
    }

    @Bean
    UsernamePasswordBasedAuthProvider usernamePasswordBasedAuthProvider() {
        return new UsernamePasswordBasedAuthProvider();
    }

    @Bean
    TokenBasedFullyAuthenticatedProvider tokenBasedFullyAuthenticatedProvider() {
        return new TokenBasedFullyAuthenticatedProvider();
    }

    @Bean
    TokenBasedPartialAuthenticatedProvider tokenBasedPartialAuthenticatedProvider() {
        return new TokenBasedPartialAuthenticatedProvider();
    }

    @Bean
    BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }


    /*
      By default, Spring OAuth2 uses HttpSessionOAuth2AuthorizationRequestRepository to save
      the authorization request. But, since our service is stateless, we can't save it in
      the session. We'll save the request in a Base64 encoded cookie instead.
    */
    @Bean
    public HttpCookieOAuth2AuthorizationRequestRepository cookieAuthorizationRequestRepository() {
        return new HttpCookieOAuth2AuthorizationRequestRepository();
    }
}
