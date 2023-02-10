package com.f1soft.campaign.web.controller.campaign;

import com.f1soft.campaign.common.dto.ServerResponse;
import com.f1soft.campaign.common.util.ResponseBuilder;
import com.f1soft.campaign.web.config.provider.LoginProvider;
import com.f1soft.campaign.web.password.PasswordService;
import com.f1soft.campaign.web.password.dto.ChangePasswordRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Slf4j
@RestController
@RequestMapping("password")
public class PasswordController {

    @Autowired
    private PasswordService passwordService;

    @PostMapping(value = "change", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> passwordChange(@NotNull @Valid @RequestBody ChangePasswordRequest changePasswordRequest) {
        ServerResponse serverResponse = passwordService.changePassword(LoginProvider.getApplicationUser(), changePasswordRequest);
        return ResponseBuilder.response(serverResponse);
    }

}
