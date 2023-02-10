package com.f1soft.campaign.web.campaign.mapper;

import com.f1soft.campaign.entities.model.CampaignEligibleUser;
import com.f1soft.campaign.web.dto.CampaignEligibleUserDTO;

/**
 * @Author Nitesh Poudel
 */
public class CampaignEligibleUserMapper {

    private CampaignEligibleUserMapper() {}

    public static CampaignEligibleUser mapToCampaignEligibleUser(CampaignEligibleUserDTO campaignEligibleUserDTO) {
        CampaignEligibleUser campaignEligibleUser = new CampaignEligibleUser();
        campaignEligibleUser.setUserName(campaignEligibleUserDTO.getMobileNumber());
        campaignEligibleUser.setPromoCode(campaignEligibleUserDTO.getPromoCode());
        campaignEligibleUser.setCampaign(campaignEligibleUserDTO.getCampaign());

        return campaignEligibleUser;
    }
}
