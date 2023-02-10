package com.f1soft.campaign.web.notification.service.impl;

import com.f1soft.campaign.common.config.application.SystemConfig;
import com.f1soft.campaign.common.config.constant.AppConfigConstant;
import com.f1soft.campaign.common.dto.RestTemplateResponse;
import com.f1soft.campaign.common.dto.ServerResponse;
import com.f1soft.campaign.common.util.DateFormat;
import com.f1soft.campaign.common.util.DateFormatter;
import com.f1soft.campaign.entities.model.Campaign;
import com.f1soft.campaign.web.notification.connector.FCMPushConnector;
import com.f1soft.campaign.web.notification.dto.*;
import com.f1soft.campaign.web.notification.manager.BankxpUserManager;
import com.f1soft.campaign.web.notification.service.FCMNotification;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@Component
@Qualifier("customUserNotification")
public class CustomUserNotification implements FCMNotification {

    @Autowired
    private SystemConfig systemConfig;
    @Autowired
    private BankxpUserManager bankxpUserManager;
    @Autowired
    private FCMPushConnector fcmPushConnector;

    @Override
    public ServerResponse sendNotification(NotificationRequest notificationRequest, Campaign campaign) {

        ServerResponse serverResponse = new ServerResponse();

        for (String mobileNumber : notificationRequest.getTestNumber()) {

            ServerResponse notificationResponse = bankxpUserManager.userDevice(mobileNumber);

            if (notificationResponse.getCode().equalsIgnoreCase("0")) {
                UserDeviceResponse userDeviceResponse = (UserDeviceResponse) notificationResponse.getObj();
                log.debug("HttpUserDeviceResponse : {}", userDeviceResponse);
                String notificationId = userDeviceResponse.getNotificationId();

                PushNotificationRequest pushNotificationRequest = buildTokenNotificationRequest(notificationRequest, campaign, notificationId);
                RestTemplateResponse restTemplateResponse = fcmPushConnector.sendFCMTokenNotification(pushNotificationRequest);

                if (restTemplateResponse.isObtained()) {

                    PushNotificationResponse pushNotificationResponse = (PushNotificationResponse) restTemplateResponse.getObj();

                    if (pushNotificationResponse.getCode().equalsIgnoreCase("0")) {

                        serverResponse.setSuccess(true);
                        serverResponse.setCode("0");
                        serverResponse.setMessage("Message submitted successfully.");

                    } else {
                        serverResponse.setSuccess(false);
                        serverResponse.setCode("2");
                        serverResponse.setMessage("Message submit failed. Remarks: Push Notification error!");
                    }
                } else {
                    serverResponse.setSuccess(false);
                    serverResponse.setCode("2");
                    serverResponse.setMessage("Message submit failed. Remarks: Push Notification error! response not obtrained from server");

                }
            } else {
                serverResponse.setSuccess(false);
                serverResponse.setCode("2");
                serverResponse.setMessage("Message submit failed. Remarks: device id not registered");
            }
        }

        return serverResponse;
    }

    private PushNotificationRequest buildTokenNotificationRequest(NotificationRequest notificationRequest, Campaign campaign, String token) {
        PushNotificationRequest pushNotificationRequest = new PushNotificationRequest();

        pushNotificationRequest.setToken(token);
        pushNotificationRequest.setTitle(notificationRequest.getTitle());
        pushNotificationRequest.setMessage(notificationRequest.getShortDescription());

        List<ExtraDataRequest> datas = new ArrayList<>();

        ExtraDataRequest tokenData = new ExtraDataRequest();
        tokenData.setKey("token");
        tokenData.setValue(token);

        datas.add(tokenData);

        if (campaign.getOfferLink() != null && campaign.getOfferLink().trim().length() > 0) {
            ExtraDataRequest bannerUrlData = new ExtraDataRequest();
            bannerUrlData.setKey("link");
            bannerUrlData.setValue(campaign.getOfferLink());

            datas.add(bannerUrlData);

            ExtraDataRequest oldbannerUrlDataB = new ExtraDataRequest();
            oldbannerUrlDataB.setKey("bannerUrl");
            oldbannerUrlDataB.setValue(campaign.getOfferLink());

            datas.add(oldbannerUrlDataB);

        }
        if (campaign.getImage() != null && campaign.getImage().trim().length() > 0) {
            String imagePath = systemConfig.appConfig(AppConfigConstant.CAMPAIGN_IMAGE_PATH);
            ExtraDataRequest bannerImageData = new ExtraDataRequest();
            bannerImageData.setKey("imagePath");
            bannerImageData.setValue(imagePath + "/" + campaign.getImage());
            datas.add(bannerImageData);


            ExtraDataRequest oldBannerImageData = new ExtraDataRequest();
            oldBannerImageData.setKey("bannerImage");
            oldBannerImageData.setValue(imagePath + "/" + campaign.getImage());

            datas.add(oldBannerImageData);

        }

        ExtraDataRequest sentDateData = new ExtraDataRequest();
        sentDateData.setKey("date");
        sentDateData.setValue(DateFormatter.changeFormat(new Date(), DateFormat.DEFAULT_DATE_FORMAT));

        datas.add(sentDateData);

        ExtraDataRequest toNotify = new ExtraDataRequest();
        toNotify.setKey("toNotify");
        toNotify.setValue("N");

        datas.add(toNotify);

        ExtraDataRequest titleData = new ExtraDataRequest();
        titleData.setKey("title");
        titleData.setValue(notificationRequest.getTitle());

        datas.add(titleData);

        ExtraDataRequest messageData = new ExtraDataRequest();
        messageData.setKey("message");
        messageData.setValue(notificationRequest.getShortDescription());

        datas.add(messageData);

        ExtraDataRequest messageDetailData = new ExtraDataRequest();
        messageDetailData.setKey("messageDetail");
        messageDetailData.setValue(notificationRequest.getDescription());

        datas.add(messageDetailData);

        pushNotificationRequest.setDatas(datas);

        return pushNotificationRequest;
    }
}
