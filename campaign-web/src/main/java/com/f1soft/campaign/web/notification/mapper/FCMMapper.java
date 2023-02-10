package com.f1soft.campaign.web.notification.mapper;

import com.f1soft.campaign.entities.model.FCMMessage;
import com.f1soft.campaign.web.config.provider.LoginProvider;
import com.f1soft.campaign.web.notification.dto.NotificationRequest;

import java.util.Date;

public class FCMMapper {

    private FCMMapper() {}

    public static FCMMessage convertToCreateFCMMessage(NotificationRequest notificationRequest, String bannerUrl, String bannerImage , String topic) {
        FCMMessage fcmMessage = new FCMMessage();
        fcmMessage.setMessage(notificationRequest.getShortDescription());
        fcmMessage.setMessageDetail(notificationRequest.getDescription());
        fcmMessage.setTitle(notificationRequest.getTitle());
        fcmMessage.setSentBy(LoginProvider.getApplicationUser().getId());
        fcmMessage.setSentDate(new Date());
        fcmMessage.setTopic(topic);
        fcmMessage.setStatus("NEW");
        fcmMessage.setBannerUrl(bannerUrl);
        fcmMessage.setBannerImage(bannerImage);
        return  fcmMessage;

    }
}
