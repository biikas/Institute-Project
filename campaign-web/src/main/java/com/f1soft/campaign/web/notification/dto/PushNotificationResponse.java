package com.f1soft.campaign.web.notification.dto;

import lombok.*;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PushNotificationResponse {
    private String code;
    private String message;
    private MessageData data;
}
