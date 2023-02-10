package com.f1soft.campaign.web.notification.manager;

import com.f1soft.campaign.common.config.application.SystemConfig;
import com.f1soft.campaign.common.config.constant.AppConfigConstant;
import com.f1soft.campaign.entities.model.Campaign;
import com.f1soft.campaign.entities.model.FCMMessage;
import com.f1soft.campaign.repository.FCMMessageRepository;
import com.f1soft.campaign.web.notification.dto.NotificationRequest;
import com.f1soft.campaign.web.notification.mapper.FCMMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class FCMManager {
    @Autowired
    private FCMMessageRepository fcmMessageRepository;

    @Autowired
    private SystemConfig systemConfig;

    public FCMMessage saveFCMMessage(NotificationRequest notificationRequest, Campaign campaign, String topic) {
        String bannerImage = "";
        String bannerUrl = "";
        if(notificationRequest.getAppendBanner() == 'Y') {
            if (!StringUtils.isEmpty(campaign.getImage())) {
                String imagePath = systemConfig.appConfig(AppConfigConstant.CAMPAIGN_IMAGE_PATH);
                bannerImage = imagePath + "/" + campaign.getImage();
            }
            if (!StringUtils.isEmpty((campaign.getOfferLink()))) {
                bannerUrl = campaign.getOfferLink();
            }
        }
        FCMMessage fcmMessage = FCMMapper.convertToCreateFCMMessage(notificationRequest, bannerUrl, bannerImage, topic);
        return fcmMessageRepository.save(fcmMessage);
    }


    public void updateFCMMessageStatus(FCMMessage fcmMessage, boolean success, String messageId, String remarks) {
        if (success) {
            fcmMessage.setStatus("NOTIFIED");
            fcmMessage.setMessageId(messageId);
        } else {
            fcmMessage.setStatus("FAILED");
        }
        fcmMessage.setRemarks(remarks);
        fcmMessageRepository.save(fcmMessage);
    }

}
