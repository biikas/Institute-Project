package com.f1soft.campaign.web.login.service.impl;

import com.f1soft.campaign.common.dto.ServerResponse;
import com.f1soft.campaign.web.auth.AuthenticationRequest;
import com.f1soft.campaign.web.login.AuthenticationMapper;
import com.f1soft.campaign.web.login.Login;
import com.f1soft.campaign.web.login.dto.LoginRequest;
import com.f1soft.campaign.web.login.service.UserAuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserAuthServiceImpl implements UserAuthService {

    @Autowired
    private Login login;

    @Override
    public ServerResponse login(LoginRequest loginRequest) {
        AuthenticationRequest authenticationRequest = AuthenticationMapper.convertToAuthenticationRequest(
                loginRequest.getUsername(),
                loginRequest.getPassword());

        return login.authenticate(authenticationRequest);
    }
}
