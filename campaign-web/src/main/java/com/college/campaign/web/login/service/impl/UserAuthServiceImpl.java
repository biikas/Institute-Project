package com.college.campaign.web.login.service.impl;

import com.college.campaign.common.dto.ServerResponse;
import com.college.campaign.web.auth.AuthenticationRequest;
import com.college.campaign.web.login.AuthenticationMapper;
import com.college.campaign.web.login.Login;
import com.college.campaign.web.login.dto.LoginRequest;
import com.college.campaign.web.login.service.UserAuthService;
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
