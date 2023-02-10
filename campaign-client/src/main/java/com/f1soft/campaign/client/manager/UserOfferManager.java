package com.f1soft.campaign.client.manager;

import com.f1soft.campaign.client.dto.request.OfferFetchRequest;
import com.f1soft.campaign.common.enums.EventTypeEnum;
import com.f1soft.campaign.common.enums.UserTypesEnum;
import com.f1soft.campaign.entities.model.Campaign;
import com.f1soft.campaign.entities.model.CampaignEligibleProfile;
import com.f1soft.campaign.entities.model.CampaignEligibleUser;
import com.f1soft.campaign.repository.CampaignEligibleProfileRepository;
import com.f1soft.campaign.repository.CampaignEligibleUserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Rashim Dhaubanjar
 */
@Slf4j
@Component
public class UserOfferManager {

    @Autowired
    private CampaignEligibleProfileRepository campaignEligibleProfileRepository;

    @Autowired
    private CampaignEligibleUserRepository campaignEligibleUserRepository;

    public boolean checkOfferEligibility(Campaign campaign, OfferFetchRequest offerFetchRequest) {

        if (campaign.getEventType().equalsIgnoreCase(EventTypeEnum.TRANSACTION.name())) {

            if (campaign.getAllowedUsers().equalsIgnoreCase(UserTypesEnum.PROFILE.name())) {
                List<CampaignEligibleProfile> campaignEligibleProfileList = campaignEligibleProfileRepository.getByCampaign(campaign.getId());
                if (!campaignEligibleProfileList.isEmpty() && offerFetchRequest.getCustomerProfileId() != null) {
                    return campaignEligibleProfileList.stream().filter(f -> f.getProfileId().equals(offerFetchRequest.getCustomerProfileId()))
                            .findAny().isPresent();
                }
            } else if (campaign.getAllowedUsers().equalsIgnoreCase(UserTypesEnum.BULK.name())) {
                List<CampaignEligibleUser> campaignEligibleUsersList = campaignEligibleUserRepository.getCampaignEligibleUserByCampaignId(campaign.getId());
                if (!campaignEligibleUsersList.isEmpty() && offerFetchRequest.getMobileNumber() != null) {
                    return campaignEligibleUsersList.stream().filter(f -> f.getUserName().equalsIgnoreCase(offerFetchRequest.getMobileNumber()))
                            .findAny().isPresent();
                }
            }
            return true;
        }
        return false;
    }
}
