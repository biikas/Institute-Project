package com.f1soft.campaign.web.controller.campaign;

import com.f1soft.campaign.common.dto.ServerResponse;
import com.f1soft.campaign.common.util.ResponseBuilder;
import com.f1soft.campaign.web.mapper.TokenMapper;
import com.f1soft.campaign.web.service.campaign.LogoutService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
public class LogoutController {

    @Autowired
    private LogoutService logoutService;

    @Autowired
    private HttpServletRequest request;

    @GetMapping(value = "admin/logout", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> logout() {
        String token = request.getHeader(HttpHeaders.AUTHORIZATION).replace("Bearer", "").trim();
        ServerResponse serverResponse = logoutService.logout(TokenMapper.convertToTokenDTO(token));
        return ResponseBuilder.response(serverResponse);
    }
}

