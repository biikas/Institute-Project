package com.nikosera.clientweb.controller;

import com.nikosera.cas.auth.request.AuthRequest;
import com.nikosera.cas.auth.request.TOTPAuthRequest;
import com.nikosera.cas.auth.response.NormalAuthResponse;
import com.nikosera.cas.service.AuthenticationService;
import com.nikosera.cas.util.HeaderManager;
import com.nikosera.common.constant.ApiConstant;
import com.nikosera.common.constant.AuthorizationGrant;
import com.nikosera.common.dto.GenericResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * @author Narayan Joshi
 * @email narenzoshi@gmail.com
 */
@Slf4j
@RestController
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ApplicationUserAuthController {

    private final AuthenticationService authenticationService;
    private final HeaderManager headerManager;

    @PostMapping(path = ApiConstant.Auth.LOGIN,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> login(@NotNull @Valid @RequestBody AuthRequest authRequest) {
        GenericResponse genericResponse = authenticationService.login(authRequest);
        NormalAuthResponse normalAuthResponse = (NormalAuthResponse) genericResponse.getData();
        return new ResponseEntity<>(genericResponse, headerManager
                .buildAuthenticationHeader(normalAuthResponse.getToken()), HttpStatus.OK);
    }

    @PreAuthorize(AuthorizationGrant.TOTP)
    @PostMapping(path = ApiConstant.Auth.VALIDATE_TOTP,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> validateTotp(@NotNull @Valid @RequestBody TOTPAuthRequest request) {
        GenericResponse genericResponse = authenticationService.verifyTotp(request);
        NormalAuthResponse normalAuthResponse = (NormalAuthResponse) genericResponse.getData();
        return new ResponseEntity<>(genericResponse, headerManager
                .buildAuthenticationHeader(normalAuthResponse.getToken()), HttpStatus.OK);
    }

}
