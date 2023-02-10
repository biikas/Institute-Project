package com.f1soft.campaign.web.notification.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MessageData {
    String messageId;
    Integer successCount;
    Integer failureCount;
}
