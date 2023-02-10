package com.f1soft.campaign.web.service.campaign.impl;

import com.f1soft.campaign.common.constant.MsgConstant;
import com.f1soft.campaign.common.dto.ServerResponse;
import com.f1soft.campaign.common.util.ResponseMsg;
import com.f1soft.campaign.web.service.campaign.LogoutService;
import com.f1soft.campaign.web.token.TokenDTO;
import com.f1soft.campaign.web.token.TokenRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class LogoutServiceImpl implements LogoutService {

    @Autowired
    private TokenRepository tokenRepository;

    @Override
    public ServerResponse logout(TokenDTO tokenDTO) {
        tokenRepository.removeToken(tokenDTO.getToken());

        ServerResponse serverResponse = ResponseMsg.successResponse(MsgConstant.Admin.LOGOUT_SUCCESS);
        return serverResponse;
    }
}

