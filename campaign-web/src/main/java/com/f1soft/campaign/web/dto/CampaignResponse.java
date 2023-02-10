package com.f1soft.campaign.web.dto;

import com.f1soft.campaign.common.dto.ModelBase;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CampaignResponse extends ModelBase {

    private Long id;
    private String campaignMode;
    private String title;
    private String shortDescription;
    private String description;
    private String imagePath;
    private String promoCode;
    private String startDate;
    private String endDate;
    private Integer totalIssued;
    private Integer usagePerCustomer;
    private char active;
    private Integer totalConsumed;
    private String status;
}
