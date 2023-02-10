package com.f1soft.campaign.web.notification.factory;

import com.f1soft.campaign.common.exception.InvalidDataException;
import com.f1soft.campaign.entities.model.Campaign;
import com.f1soft.campaign.entities.model.CampaignEligibleProfile;
import com.f1soft.campaign.repository.CampaignEligibleProfileRepository;
import com.f1soft.campaign.web.notification.service.FCMNotification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class NotificationFactory {

    @Autowired
    private CampaignEligibleProfileRepository campaignEligibleProfileRepository;
    @Autowired
    @Qualifier("allUserNotification")
    private FCMNotification allUserNotification;
    @Autowired
    @Qualifier("customUserNotification")
    private FCMNotification customUserNotification;

    public FCMNotification getFCMNotification(Campaign campaign, char isTestNotification) {

        if (isTestNotification == 'Y') {
            return customUserNotification;
        } else {
            List<CampaignEligibleProfile> campaignEligibleProfileList = campaignEligibleProfileRepository.getByCampaign(campaign.getId());
            if (campaignEligibleProfileList.isEmpty()) {
                return allUserNotification;
            } else {
                throw new InvalidDataException("FCM supported for all user campaign only.");
            }
        }
    }

}
