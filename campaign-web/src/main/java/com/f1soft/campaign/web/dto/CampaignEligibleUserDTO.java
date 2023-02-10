package com.f1soft.campaign.web.dto;

import com.f1soft.campaign.entities.model.Campaign;
import lombok.Getter;
import lombok.Setter;

/**
 * @Author Nitesh Poudel
 */
@Getter
@Setter
public class CampaignEligibleUserDTO {

    private String mobileNumber;
    private String promoCode;
    private Campaign campaign;
}
