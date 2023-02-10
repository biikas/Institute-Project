package com.f1soft.campaign.web.notification.connector;

import com.f1soft.campaign.common.config.application.SystemConfig;
import com.f1soft.campaign.common.config.constant.NotificationConfigConstant;
import com.f1soft.campaign.common.dto.RestTemplateResponse;
import com.f1soft.campaign.common.http.RestNxTemplate;
import com.f1soft.campaign.common.util.RestHelper;
import com.f1soft.campaign.web.notification.dto.PushNotificationRequest;
import com.f1soft.campaign.web.notification.dto.PushNotificationResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class FCMPushConnector extends RestNxTemplate {

    @Autowired
    private SystemConfig systemConfig;

    private static final String PUSH_NOTIFICATION_TOKEN = "push/notification/token";
    private static final String PUSH_NOTIFICATION_TOPIC = "push/notification/topic";


    public RestTemplateResponse sendFCMTokenNotification(PushNotificationRequest pushNotificationRequest) {
        String baseUrl = systemConfig.notificationConfig(NotificationConfigConstant.FCM_NOTIFICATION_PUSH_URL);
        String url = baseUrl + PUSH_NOTIFICATION_TOKEN;

        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(MediaType.APPLICATION_JSON);

        return doPost(
                url,
                RestHelper.buildEntity(pushNotificationRequest),
                new ParameterizedTypeReference<PushNotificationResponse>() {
                });
    }

    public RestTemplateResponse sendFCMTopicNotification(PushNotificationRequest pushNotificationRequest) {
        String baseUrl = systemConfig.notificationConfig(NotificationConfigConstant.FCM_NOTIFICATION_PUSH_URL);
        String url = baseUrl + PUSH_NOTIFICATION_TOPIC;

        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(MediaType.APPLICATION_JSON);

        return doPost(
                url,
                RestHelper.buildEntity(pushNotificationRequest),
                new ParameterizedTypeReference<PushNotificationResponse>() {
                });
    }
}
