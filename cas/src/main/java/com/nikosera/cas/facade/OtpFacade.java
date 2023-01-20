package com.nikosera.cas.facade;

import com.nikosera.cas.auth.request.TOTPAuthRequest;
import com.nikosera.cas.auth.response.NormalAuthResponse;
import com.nikosera.cas.builder.AuthPermissionBuilder;
import com.nikosera.cas.builder.AuthUserBuilder;
import com.nikosera.cas.token.PartiallyAuthenticatedToken;
import com.nikosera.cas.totp.code.verifier.CodeVerifier;
import com.nikosera.cas.util.AuthenticationUtil;
import com.nikosera.cas.util.Token;
import com.nikosera.cas.util.TokenBuilder;
import com.nikosera.common.builder.ResponseBuilder;
import com.nikosera.common.dto.CustomUserDetail;
import com.nikosera.common.dto.GenericResponse;
import com.nikosera.common.exception.InvalidOTPException;
import com.nikosera.common.exception.UnauthorizedException;
import com.nikosera.entity.ApplicationUser;
import com.nikosera.repository.repository.core.ApplicationUserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import static com.nikosera.common.constant.MsgConstant.Authorization.*;

/**
 * @author Narayan Joshi
 * @email narenzoshi@gmail.com
 */
@Slf4j
@Component
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class OtpFacade {

    private final TokenBuilder tokenBuilder;
    private final CodeVerifier codeVerifier;
    private final AuthenticationUtil authenticationUtil;
    private final ApplicationUserRepository applicationUserRepository;

    public GenericResponse validate(TOTPAuthRequest request) {
        if (isAuthenticated()) { // Check if Authenticated
            PartiallyAuthenticatedToken authentication = (PartiallyAuthenticatedToken) SecurityContextHolder.getContext().getAuthentication();
            CustomUserDetail customUserDetail = (CustomUserDetail) authentication.getPrincipal();
            if (isValidCode(customUserDetail, request)) { // Validate OTP token
                updateUserLogin(customUserDetail, authentication);
                AuthPermissionBuilder.buildForFullyAuthenticated(customUserDetail);
                return buildResponse(customUserDetail);
            }
        }
        return ResponseBuilder.failureResponse(UNAUTHORIZED);
    }

    public boolean isAuthenticated() {
        if (SecurityContextHolder.getContext().getAuthentication() == null)
            throw new UnauthorizedException(UNAUTHORIZED);
        return true;
    }

    public boolean isValidCode(CustomUserDetail customUserDetail, TOTPAuthRequest request) {
        boolean isValid = codeVerifier.isValidCode(customUserDetail.getSecretKey(), request.getToken());
        if (!isValid) {
            throw new InvalidOTPException(INVALID_OTP);
        }
        return true;
    }

    public void updateUserLogin(CustomUserDetail customUserDetail, PartiallyAuthenticatedToken authentication) {
        ApplicationUser applicationUser = customUserDetail.getCurrentUser();
        if (!customUserDetail.isMfaEnabled() && authentication.isFirstTimeTotpAuthentication()) { // first time totp verification checks
            AuthUserBuilder.buildFirstTimeTOTPAuthUser(applicationUser);
            applicationUserRepository.save(applicationUser);
        }
        authenticationUtil.updateUser(applicationUser); // reset wrong attempt counters
    }

    public GenericResponse buildResponse(CustomUserDetail customUserDetail) {
        Token token = tokenBuilder.normalAuthToken(customUserDetail);
        NormalAuthResponse normalAuthResponse = new NormalAuthResponse();
        normalAuthResponse.setToken(token.getToken());
        normalAuthResponse.setAccessTime(token.getCreatedDate());
        normalAuthResponse.setAccessExpiryTime(token.getExpiryDate());

        return ResponseBuilder.buildSuccessMessage(normalAuthResponse, CORRECT_CREDENTIAL);
    }
}
