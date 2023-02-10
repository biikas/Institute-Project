package com.f1soft.campaign.web.controller.campaign;

import com.f1soft.campaign.common.dto.ServerResponse;
import com.f1soft.campaign.common.util.ResponseBuilder;
import com.f1soft.campaign.web.notification.dto.NotificationRequest;
import com.f1soft.campaign.web.notification.service.FCMService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;


@RestController
@RequestMapping("notification")
public class NotificationController {

    @Autowired
    private FCMService fcmService;

    @PostMapping(value = "send", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> sendNotification(@NotNull @Valid @RequestBody NotificationRequest notificationRequest) {
        ServerResponse serverResponse = fcmService.sendFCMMessage(notificationRequest);
        return ResponseBuilder.response(serverResponse);
    }

}
