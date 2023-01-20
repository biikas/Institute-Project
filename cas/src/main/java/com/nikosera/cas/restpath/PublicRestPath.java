package com.nikosera.cas.restpath;

import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
/**
 * @author Narayan Joshi
 * @email narenzoshi@gmail.com
 */
public class PublicRestPath {
    public static final RequestMatcher PUBLIC_UI_URLS = new OrRequestMatcher(
            new AntPathRequestMatcher("/public/**"),
            new AntPathRequestMatcher("/error/**"),
            new AntPathRequestMatcher("/swagger-ui.html"),
            new AntPathRequestMatcher("/swagger-resources/**"),
            new AntPathRequestMatcher("/v2/api-docs/**"),
            new AntPathRequestMatcher("/swagger-resources/configuration/ui"),
            new AntPathRequestMatcher("/*.html"),
            new AntPathRequestMatcher("/**/*.html"),
            new AntPathRequestMatcher("/**/*.css"),
            new AntPathRequestMatcher("/**/*.js"),
            new AntPathRequestMatcher("/**/*.png"),
            new AntPathRequestMatcher("/**/*.ttf"),
            new AntPathRequestMatcher("/**/*.gif"),
            new AntPathRequestMatcher("/actuator/**")
    );

    public static final RequestMatcher NORMAL_AUTH_URLS = new OrRequestMatcher(
            new AntPathRequestMatcher("/login"),
            new AntPathRequestMatcher("/validate/otp"),
            new AntPathRequestMatcher("/echo")
    );
}
