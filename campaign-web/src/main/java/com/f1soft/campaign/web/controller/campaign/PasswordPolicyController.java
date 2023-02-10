package com.f1soft.campaign.web.controller.campaign;


import com.f1soft.campaign.common.dto.ServerResponse;
import com.f1soft.campaign.common.log.SkipAPILogging;
import com.f1soft.campaign.common.util.ResponseBuilder;
import com.f1soft.campaign.web.password.PasswordPolicyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("password/policies")
public class PasswordPolicyController {

    @Autowired
    private PasswordPolicyService passwordPolicyService;

    @SkipAPILogging
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> passwordPolicy() {
        ServerResponse serverResponse = passwordPolicyService.passwordPolicy();
        return ResponseBuilder.response(serverResponse);
    }
}

