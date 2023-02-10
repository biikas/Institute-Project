package com.f1soft.campaign.web.notification.service.impl;

import com.f1soft.campaign.common.config.application.SystemConfig;
import com.f1soft.campaign.common.config.constant.AppConfigConstant;
import com.f1soft.campaign.common.dto.RestTemplateResponse;
import com.f1soft.campaign.common.dto.ServerResponse;
import com.f1soft.campaign.common.util.DateFormat;
import com.f1soft.campaign.common.util.DateFormatter;
import com.f1soft.campaign.entities.model.Campaign;
import com.f1soft.campaign.entities.model.FCMMessage;
import com.f1soft.campaign.web.constant.NotificationConfigConstant;
import com.f1soft.campaign.web.notification.connector.FCMPushConnector;
import com.f1soft.campaign.web.notification.dto.*;
import com.f1soft.campaign.web.notification.manager.FCMManager;
import com.f1soft.campaign.web.notification.service.FCMNotification;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@Component
@Qualifier("allUserNotification")
public class AllUserNotification implements FCMNotification {

    @Autowired
    private SystemConfig systemConfig;
    @Autowired
    private FCMPushConnector fcmPushConnector;
    @Autowired
    private FCMManager fcmManager;

    @Override
    public ServerResponse sendNotification(NotificationRequest notificationRequest, Campaign campaign) {

        PushNotificationRequest pushNotificationRequest = buildTopicNotificationRequest(notificationRequest, campaign);

        FCMMessage fcmMessage = fcmManager.saveFCMMessage(notificationRequest, campaign, pushNotificationRequest.getTopic());

        RestTemplateResponse restTemplateResponse = fcmPushConnector.sendFCMTopicNotification(pushNotificationRequest);

        ServerResponse msgResponse = new ServerResponse();
        String messageId = "";
        if (restTemplateResponse.isObtained()) {

            PushNotificationResponse pushNotificationResponse = (PushNotificationResponse) restTemplateResponse.getObj();

            if (pushNotificationResponse.getCode().equalsIgnoreCase("0")) {
                MessageData messageData = pushNotificationResponse.getData();

                messageId = messageData.getMessageId();
                log.info("Push Notification Response  {}: " + messageData);
                msgResponse.setSuccess(true);
                msgResponse.setCode("0");
                msgResponse.setMessage("Message submitted successfully.");
                msgResponse.setObj(messageData);
            } else {
                msgResponse.setSuccess(false);
                msgResponse.setCode("");
                msgResponse.setMessage("Message submit failed. Remarks: Push Notification error!");
            }
        } else {
            msgResponse.setSuccess(false);
            msgResponse.setCode("2");
            msgResponse.setMessage("Message submit failed. Remarks: Push Notification error! response not obtained from server");
        }

        fcmManager.updateFCMMessageStatus(fcmMessage, msgResponse.isSuccess(), messageId, msgResponse.getMessage());

        return msgResponse;
    }

    private PushNotificationRequest buildTopicNotificationRequest(NotificationRequest notificationRequest, Campaign campaign) {

        PushNotificationRequest pushNotificationRequest = new PushNotificationRequest();

        String topic = systemConfig.notificationConfig(NotificationConfigConstant.FCM_TOPIC);

        pushNotificationRequest.setTopic(topic);
        pushNotificationRequest.setTitle(notificationRequest.getTitle());
        pushNotificationRequest.setMessage(notificationRequest.getShortDescription());

        List<ExtraDataRequest> datas = new ArrayList<>();

        ExtraDataRequest topicData = new ExtraDataRequest();
        topicData.setKey("topic");
        topicData.setValue(topic);

        datas.add(topicData);

        if (!StringUtils.isEmpty(campaign.getOfferLink())) {
            ExtraDataRequest bannerUrlData = new ExtraDataRequest();
            bannerUrlData.setKey("link");
            bannerUrlData.setValue(campaign.getOfferLink());

            datas.add(bannerUrlData);

            ExtraDataRequest oldbannerUrlDataB = new ExtraDataRequest();
            oldbannerUrlDataB.setKey("bannerUrl");
            oldbannerUrlDataB.setValue(campaign.getOfferLink());

            datas.add(oldbannerUrlDataB);
        }
        if (!StringUtils.isEmpty(campaign.getImage())) {
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
