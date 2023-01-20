package com.nikosera.cas.service.impl;

import com.nikosera.cas.auth.response.FirstLoginAuthResponse;
import com.nikosera.cas.service.AuthenticationResponse;
import com.nikosera.cas.totp.qr.QrBuilder;
import com.nikosera.cas.util.Token;
import com.nikosera.cas.util.TokenBuilder;
import com.nikosera.common.builder.ResponseBuilder;
import com.nikosera.common.dto.CustomUserDetail;
import com.nikosera.common.dto.GenericResponse;
import com.nikosera.entity.ApplicationUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import static com.nikosera.common.constant.MsgConstant.Authorization.CORRECT_CREDENTIAL;

/**
 * @author Narayan Joshi
 * @email narenzoshi@gmail.com
 */
@Service
@Qualifier("qrResponse")
@Slf4j
public class QrLoginResponse extends AuthenticationResponse {

    private final QrBuilder qrBuilder;

    @Autowired
    public QrLoginResponse(TokenBuilder tokenBuilder, QrBuilder qrBuilder) {
        super(tokenBuilder);
        this.qrBuilder = qrBuilder;
    }

    @Override
    public GenericResponse authResponse(CustomUserDetail customUserDetail) {
        log.debug("Qr based login response");
        ApplicationUser applicationUser = customUserDetail.getCurrentUser();

        FirstLoginAuthResponse firstLoginAuthResponse = new FirstLoginAuthResponse();
        String qr = qrBuilder.build(applicationUser);
        firstLoginAuthResponse.setQr(qr);
        firstLoginAuthResponse.setIsMfaEnabled(customUserDetail.getCurrentUser().getIsMfaEnabled());

        Token token = tokenBuilder.buildToken(customUserDetail);
        firstLoginAuthResponse.setToken(token.getToken());
        firstLoginAuthResponse.setAccessTime(token.getCreatedDate());
        firstLoginAuthResponse.setAccessExpiryTime(token.getExpiryDate());

        return ResponseBuilder.buildSuccessMessage(firstLoginAuthResponse, CORRECT_CREDENTIAL);
    }
}
