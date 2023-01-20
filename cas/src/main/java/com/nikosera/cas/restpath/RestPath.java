package com.nikosera.cas.restpath;

import org.springframework.security.web.util.matcher.*;

import static com.nikosera.cas.restpath.PublicRestPath.NORMAL_AUTH_URLS;
import static com.nikosera.cas.restpath.PublicRestPath.PUBLIC_UI_URLS;

/**
 * @author Narayan Joshi
 * @email narenzoshi@gmail.com
 */
public class RestPath {
    public static final RequestMatcher PUBLIC_URLS = new OrRequestMatcher(
            PUBLIC_UI_URLS,
            NORMAL_AUTH_URLS
    );
    public static final RequestMatcher JWT_URLS = new AndRequestMatcher(
            new NegatedRequestMatcher(PUBLIC_URLS),
            new NegatedRequestMatcher(new AntPathRequestMatcher("/v2/api-docs/")),
            new NegatedRequestMatcher(new AntPathRequestMatcher("/v2/**"))
    );
}
