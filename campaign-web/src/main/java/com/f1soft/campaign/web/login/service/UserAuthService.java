package com.f1soft.campaign.web.login.service;


import com.f1soft.campaign.common.dto.ServerResponse;
import com.f1soft.campaign.web.login.dto.LoginRequest;

/*
 * @Author Rashim Dhaubanjar
 */
public interface UserAuthService {
    ServerResponse login(LoginRequest loginRequest);
}
