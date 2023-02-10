package com.f1soft.campaign.web.notification.dto;

import com.f1soft.campaign.common.dto.ModelBase;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDeviceResponse extends ModelBase {
    private String notificationId;
    private String allowNotification;
    private String osType;
}
