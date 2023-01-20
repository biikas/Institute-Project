package com.nikosera.cas.provider;

import com.nikosera.cas.builder.AuthPermissionBuilder;
import com.nikosera.cas.token.FullyAuthenticatedToken;
import com.nikosera.cas.util.JwtToken;
import com.nikosera.cas.util.TokenManager;
import com.nikosera.cas.util.TokenUtil;
import com.nikosera.common.aop.MethodLogging;
import com.nikosera.common.dto.CustomUserDetail;
import com.nikosera.common.exception.InvalidTokenException;
import com.nikosera.common.exception.UnauthorizedException;
import com.nimbusds.jwt.JWTClaimsSet;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import static com.nikosera.common.constant.MsgConstant.Authorization.JWT;
import static com.nikosera.common.constant.MsgConstant.Authorization.UNAUTHORIZED;

/**
 * @author Narayan Joshi
 * @email narenzoshi@gmail.com
 */
@Slf4j
@Component
public class TokenBasedFullyAuthenticatedProvider implements AuthenticationProvider {

    @Autowired
    @Qualifier("usernameUserDetailService")
    private UserDetailsService userDetailsService;
    @Autowired
    private TokenUtil tokenUtil;
    @Autowired
    private TokenManager tokenManager;

    @MethodLogging
    @SneakyThrows
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        FullyAuthenticatedToken fullyAuthenticatedToken = (FullyAuthenticatedToken) authentication;
        String authToken = fullyAuthenticatedToken.getToken();

        final JWTClaimsSet jwtClaimsSet = tokenManager.parseEncryptedToken(authToken);
        JwtToken jwtToken = new JwtToken(jwtClaimsSet);
        String username = jwtToken.getUsername();

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            CustomUserDetail customUserDetail = (CustomUserDetail) userDetailsService.loadUserByUsername(username);
            if (tokenUtil.validateForUsername(jwtToken, customUserDetail)) {
                AuthPermissionBuilder.buildForFullyAuthenticated(customUserDetail);
                fullyAuthenticatedToken = new FullyAuthenticatedToken(customUserDetail, customUserDetail.getPassword(), customUserDetail.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(fullyAuthenticatedToken);
            } else {
                throw new InvalidTokenException(JWT.INVALID_TOKEN);
            }
        } else {
            throw new UnauthorizedException(UNAUTHORIZED);
        }
        return fullyAuthenticatedToken;
    }

    @Override
    public boolean supports(Class<?> type) {
        return type.equals(FullyAuthenticatedToken.class);
    }

}
