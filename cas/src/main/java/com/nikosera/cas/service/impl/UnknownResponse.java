package com.nikosera.cas.service.impl;

import com.nikosera.cas.service.AuthenticationResponse;
import com.nikosera.cas.util.TokenBuilder;
import com.nikosera.common.builder.ResponseBuilder;
import com.nikosera.common.dto.CustomUserDetail;
import com.nikosera.common.dto.GenericResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import static com.nikosera.common.constant.MsgConstant.Authorization.SYSTEM_ERROR;

/**
 * @author Narayan Joshi
 * @email narenzoshi@gmail.com
 */
@Service
@Qualifier("unknownResponse")
@Slf4j
public class UnknownResponse extends AuthenticationResponse {

    @Autowired
    public UnknownResponse(TokenBuilder tokenBuilder) {
        super(tokenBuilder);
    }

    @Override
    public GenericResponse authResponse(CustomUserDetail customUserDetail) {
        return ResponseBuilder.buildSuccessMessage(SYSTEM_ERROR);
    }
}
