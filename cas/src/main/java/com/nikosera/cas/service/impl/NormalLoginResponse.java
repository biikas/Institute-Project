package com.nikosera.cas.service.impl;

import com.nikosera.cas.auth.response.NormalAuthResponse;
import com.nikosera.cas.service.AuthenticationResponse;
import com.nikosera.cas.util.Token;
import com.nikosera.cas.util.TokenBuilder;
import com.nikosera.common.builder.ResponseBuilder;
import com.nikosera.common.dto.CustomUserDetail;
import com.nikosera.common.dto.GenericResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import static com.nikosera.common.constant.MsgConstant.Authorization.CORRECT_CREDENTIAL;

/**
 * @author Narayan Joshi
 * @email narenzoshi@gmail.com
 */
@Slf4j
@Service
@Qualifier("normalResponse")
public class NormalLoginResponse extends AuthenticationResponse {

    public NormalLoginResponse(TokenBuilder tokenBuilder) {
        super(tokenBuilder);
    }

    @Override
    public GenericResponse authResponse(CustomUserDetail customUserDetail) {
        Token token = tokenBuilder.buildToken(customUserDetail);

        NormalAuthResponse normalAuthResponse = new NormalAuthResponse();
        normalAuthResponse.setToken(token.getToken());
        if(customUserDetail.isMfaEnabled()){
            normalAuthResponse.setIsMfaEnabled(customUserDetail.getCurrentUser().getIsMfaEnabled());
        }
        normalAuthResponse.setAccessTime(token.getCreatedDate());
        normalAuthResponse.setAccessExpiryTime(token.getExpiryDate());

        return ResponseBuilder.buildSuccessMessage(normalAuthResponse, CORRECT_CREDENTIAL);
    }
}
