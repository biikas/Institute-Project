package com.nikosera.cas.service.impl;

import com.nikosera.cas.auth.request.AuthRequest;
import com.nikosera.cas.auth.request.TOTPAuthRequest;
import com.nikosera.cas.config.properties.CasFileConfig;
import com.nikosera.cas.constant.AuthMode;
import com.nikosera.cas.facade.OtpFacade;
import com.nikosera.cas.factory.AuthFactory;
import com.nikosera.cas.service.AuthenticationService;
import com.nikosera.common.builder.ConditionalResponseBuilder;
import com.nikosera.common.dto.CustomUserDetail;
import com.nikosera.common.dto.GenericResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class AuthenticationServiceImpl implements AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final CasFileConfig config;
    private final AuthFactory authFactory;
    private final OtpFacade otpFacade;

    @Override
    public GenericResponse login(AuthRequest request) {
        // Authenticate user credentials
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        CustomUserDetail customUserDetail = (CustomUserDetail) authentication.getPrincipal();
        return (GenericResponse) ConditionalResponseBuilder.builder()
                .iif(config.getTotp().getForce() && !customUserDetail.hasForcedMfa())
                .then(() -> authFactory.authenticationResponse(AuthMode.QR).authResponse(customUserDetail))
                .orElse(() -> authFactory.authenticationResponse(AuthMode.NORMAL).authResponse(customUserDetail));
    }

    @Override
    public GenericResponse verifyTotp(TOTPAuthRequest request) {
        return otpFacade.validate(request);
    }
}
