package com.nikosera.cas.provider;

import com.nikosera.cas.builder.AuthPermissionBuilder;
import com.nikosera.cas.config.properties.CasFileConfig;
import com.nikosera.cas.util.AuthenticationUtil;
import com.nikosera.common.aop.MethodLogging;
import com.nikosera.common.constant.MsgConstant;
import com.nikosera.common.dto.CustomUserDetail;
import com.nikosera.common.exception.UserTemporarilyBlockedException;
import com.nikosera.entity.ApplicationUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * @author Narayan Joshi
 * @email narenzoshi@gmail.com
 */
@Slf4j
@Component
public class UsernamePasswordBasedAuthProvider implements AuthenticationProvider {
    @Qualifier("usernameUserDetailService")
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationUtil authenticationUtil;
    @Autowired
    private CasFileConfig config;

    @MethodLogging
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        log.debug("Authenticating for username: {} with password: {}", authentication.getName(), authentication.getCredentials());

        CustomUserDetail customUserDetail = (CustomUserDetail) userDetailsService.loadUserByUsername(authentication.getName());

        boolean isValidUsername = validateUsername(authentication, customUserDetail);

        if (isValidUsername) {
            log.debug("Authentication success: Username is valid");
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(customUserDetail, customUserDetail.getPassword(), customUserDetail.getAuthorities());
            log.debug("<--Setting authenticated user in threadLocal-->");
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            boolean isValidPassword = validatePassword(authentication, customUserDetail);
            log.debug("Authentication success: Password is valid");
            AuthPermissionBuilder.buildForUsernamePasswordValidatedTOTPDisabled(customUserDetail, config.getTotp().getForce());
            log.debug("<--Authenticated user set in the threadLocal-->");
            return usernamePasswordAuthenticationToken;

        }
        return authentication;
    }

    @MethodLogging
    private boolean validateUsername(Authentication currentUser, CustomUserDetail storedUser) {
        if (storedUser.getUsername().equals(currentUser.getName())) {
            ApplicationUser applicationUser = storedUser.getCurrentUser();
            if (applicationUser.getActive() == 'N') {
                log.debug("Authentication failed: user is blocked");
                throw new UserTemporarilyBlockedException(MsgConstant.Authorization.BLOCKED);
            }
        } else {
            log.debug("Authentication failed: Username is not correct");
            throw new UserTemporarilyBlockedException(MsgConstant.Authorization.INCORRECT_CREDENTIAL);
        }
        return true;
    }

    @MethodLogging
    private boolean validatePassword(Authentication currentUser, CustomUserDetail storedUser) {
        if (currentUser.getCredentials() == null) {
            log.debug("Authentication failed: no credentials provided");
            authenticationUtil.increaseIncorrectDetailAttempt(storedUser);
        } else {
            String presentedPassword = currentUser.getCredentials().toString();
            if (!this.passwordEncoder.matches(presentedPassword, storedUser.getPassword())) {
                authenticationUtil.increaseIncorrectDetailAttempt(storedUser);
                log.debug("Authentication failed: password does not match stored value");
            }
        }
        authenticationUtil.updateUser(storedUser.getCurrentUser());
        return true;
    }

    @Override
    public boolean supports(Class<?> type) {
        return type.equals(UsernamePasswordAuthenticationToken.class);
    }
}
