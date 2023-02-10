package com.f1soft.campaign.web.notification.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class PushNotificationRequest {

    private String title;
    private String message;
    private String topic;
    private String token;

    List<ExtraDataRequest> datas;

}