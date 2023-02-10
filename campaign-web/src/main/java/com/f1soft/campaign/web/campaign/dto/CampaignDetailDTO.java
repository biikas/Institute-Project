package com.f1soft.campaign.web.campaign.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Shreetika Panta
 */

@Getter
@Setter
public class CampaignDetailDTO {

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

    private Double spendingLimit;
    private Double spentPercentage;
    private Double spentAmount;
    private Integer redeemLimit;
    private Integer redeemCount;
    private Double redeemPercentage;
}
