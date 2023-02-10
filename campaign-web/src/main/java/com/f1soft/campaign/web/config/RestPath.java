package com.f1soft.campaign.web.config;


import org.springframework.security.web.util.matcher.*;

/*
 * @Author Rashim Dhaubanjar
 */
public class RestPath {

    public static final RequestMatcher PUBLIC_URLS = new OrRequestMatcher(
            new AntPathRequestMatcher("/public/**"),
            new AntPathRequestMatcher("/error/**"),
            new AntPathRequestMatcher("/swagger-ui.html"),
            new AntPathRequestMatcher("/swagger-resources/**"),
            new AntPathRequestMatcher("/v2/api-docs/**"),
            new AntPathRequestMatcher("/swagger-resources/configuration/ui"),
            new AntPathRequestMatcher("/webjars/springfox-swagger-ui/**"),
            new AntPathRequestMatcher("/*.html"),
            new AntPathRequestMatcher("/**/*.html"),
            new AntPathRequestMatcher("/**/*.css"),
            new AntPathRequestMatcher("/**/*.js"),
            new AntPathRequestMatcher("/**/*.png"),
            new AntPathRequestMatcher("/**/*.ttf"),
            new AntPathRequestMatcher("/**/*.gif"),
            new AntPathRequestMatcher("/actuator/**"),
            new AntPathRequestMatcher("/auth/**"),
            new AntPathRequestMatcher("/"),
            new AntPathRequestMatcher("/public/**"),
            new AntPathRequestMatcher("/offer/list"),
            new AntPathRequestMatcher("/offer/user/list"),
            new AntPathRequestMatcher("/start/job"),
            new AntPathRequestMatcher("/refund")
    );

    public static final RequestMatcher HMAC_URLS = new OrRequestMatcher(
            new AntPathRequestMatcher("/offer/book/code"),
            new AntPathRequestMatcher("/offer/transaction"),
            new AntPathRequestMatcher("/redeem")
    );

    public static final RequestMatcher JWT_URLS = new AndRequestMatcher(
            new NegatedRequestMatcher(PUBLIC_URLS),
            new NegatedRequestMatcher(HMAC_URLS),
            new AntPathRequestMatcher("/**")
    );
}
