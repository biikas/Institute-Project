package com.college.campaign.web.campaign.mapper;

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
