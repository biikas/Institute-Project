package com.college.campaign.web.controller;

import com.college.campaign.web.login.dto.LoginRequest;
import com.college.campaign.web.login.dto.LoginResponse;
import com.college.campaign.web.login.service.UserAuthService;
import com.college.campaign.common.dto.ServerResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

@Slf4j
@RestController
public class AuthController {

    @Autowired
    private UserAuthService authService;

    @PostMapping(value = "auth/login", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> login(@NotNull @RequestBody LoginRequest loginRequest) {
        ServerResponse serverResponse = authService.login(loginRequest);

        LoginResponse loginResponse = (LoginResponse) serverResponse.getObj();
        loginResponse.setSuccess(serverResponse.isSuccess());
        loginResponse.setMessage(serverResponse.getMessage());
        HttpHeaders headers = new HttpHeaders();

        headers.add(HttpHeaders.AUTHORIZATION, loginResponse.getToken());

        return new ResponseEntity<>(loginResponse, headers, HttpStatus.OK);
    }
}
