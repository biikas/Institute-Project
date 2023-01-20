package com.nikosera.cas.filter.authentication;

import com.nikosera.cas.token.FullyAuthenticatedToken;
import com.nikosera.cas.util.BearerTokenHeaderUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
/**
 * @author Narayan Joshi
 * @email narenzoshi@gmail.com
 */
@Slf4j
public class FullyAuthenticatedTokenAuthorizationFilter extends AbstractAuthenticationProcessingFilter {

    @Autowired
    private BearerTokenHeaderUtil bearerTokenHeaderUtil;

    public FullyAuthenticatedTokenAuthorizationFilter(final RequestMatcher requiresAuth) {
        super(requiresAuth);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,final HttpServletResponse response) {
        if (SecurityContextHolder.getContext().getAuthentication() == null) {
            log.info("Intercepted by authenticated token filter");
            String token = bearerTokenHeaderUtil.extractToken(request);

            final FullyAuthenticatedToken authentication = new FullyAuthenticatedToken(token);
            
            return getAuthenticationManager().authenticate(authentication);
        }
        return SecurityContextHolder.getContext().getAuthentication();
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        super.successfulAuthentication(request, response, chain, authResult);
        chain.doFilter(request, response);
    }
}
