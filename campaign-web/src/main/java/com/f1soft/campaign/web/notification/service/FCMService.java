package com.f1soft.campaign.web.notification.service;

import com.f1soft.campaign.common.dto.ServerResponse;
import com.f1soft.campaign.web.notification.dto.NotificationRequest;

public interface FCMService {

    ServerResponse sendFCMMessage(NotificationRequest pushNotificationRequest);

}
