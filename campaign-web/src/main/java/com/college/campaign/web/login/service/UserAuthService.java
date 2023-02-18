package com.college.campaign.web.login.service;


import com.college.campaign.common.dto.ServerResponse;
import com.college.campaign.web.login.dto.LoginRequest;

/*
 * @Author Rashim Dhaubanjar
 */
public interface UserAuthService {
    ServerResponse login(LoginRequest loginRequest);
}
