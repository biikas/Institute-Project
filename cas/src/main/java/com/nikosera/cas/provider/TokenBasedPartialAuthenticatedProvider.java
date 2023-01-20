package com.nikosera.cas.provider;

import com.nikosera.cas.builder.AuthPermissionBuilder;
import com.nikosera.cas.token.PartiallyAuthenticatedToken;
import com.nikosera.cas.util.JwtToken;
import com.nikosera.cas.util.TokenManager;
import com.nikosera.cas.util.TokenUtil;
import com.nikosera.common.aop.MethodLogging;
import com.nikosera.common.dto.CustomUserDetail;
import com.nikosera.common.exception.InvalidTokenException;
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

/**
 * @author Narayan Joshi
 * @email narenzoshi@gmail.com
 */
@Slf4j
@Component
public class TokenBasedPartialAuthenticatedProvider implements AuthenticationProvider {

    @Autowired
    @Qualifier("secretKeyUserDetailService")
    private UserDetailsService userDetailsService;
    @Autowired
    private TokenUtil tokenUtil;
    @Autowired
    private TokenManager tokenManager;

    @MethodLogging
    @SneakyThrows
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String authToken = ((PartiallyAuthenticatedToken) authentication).getToken();

        final JWTClaimsSet jwtClaimsSet = tokenManager.parseEncryptedToken(authToken);
        JwtToken jwtToken = new JwtToken(jwtClaimsSet);

        String secret = jwtToken.getSecret();
        if (secret != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            CustomUserDetail customUserDetail = (CustomUserDetail) userDetailsService.loadUserByUsername(secret);
            if (tokenUtil.validateForSecretKey(jwtToken, customUserDetail)) {
                log.debug("Authentication success: TOTP token is valid");
                Boolean isFirstTimeTotpAuthentication = jwtToken.isTotpEnabled();

                AuthPermissionBuilder.buildUsernamePasswordValidatedTOTPEnabled(customUserDetail);
                PartiallyAuthenticatedToken partiallyAuthenticatedToken = new PartiallyAuthenticatedToken(customUserDetail, customUserDetail.getPassword(), customUserDetail.getAuthorities(), isFirstTimeTotpAuthentication);
                log.debug("<--Setting partially authenticated  n threadLocal-->");
                SecurityContextHolder.getContext().setAuthentication(partiallyAuthenticatedToken);
                log.debug("<--Partially authenticated user set in the threadLocal-->");
                return partiallyAuthenticatedToken;
            } else {
                throw new InvalidTokenException("Invalid OTP");
            }
        }
        return authentication;
    }

    @Override
    public boolean supports(Class<?> type) {
        return type.equals(PartiallyAuthenticatedToken.class);
    }
}
