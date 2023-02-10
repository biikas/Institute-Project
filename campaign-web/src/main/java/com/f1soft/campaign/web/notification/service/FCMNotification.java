package com.f1soft.campaign.web.notification.service;

import com.f1soft.campaign.common.dto.ServerResponse;
import com.f1soft.campaign.entities.model.Campaign;
import com.f1soft.campaign.web.notification.dto.NotificationRequest;

public interface FCMNotification {

    ServerResponse sendNotification(NotificationRequest notificationRequest, Campaign campaign);
}
