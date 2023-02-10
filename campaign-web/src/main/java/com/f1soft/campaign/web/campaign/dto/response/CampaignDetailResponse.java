package com.f1soft.campaign.web.campaign.dto.response;

import com.f1soft.campaign.web.campaign.dto.NtransactionDTO;
import com.f1soft.campaign.web.dto.ProfileDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CampaignDetailResponse {

    private Long id;
    private String title;
    private String shortDescription;
    private String description;
    private String promoCode;
    private String policy;
    private String startDate;
    private String endDate;
    private Integer totalCodeToIssue;
    private Integer usageLimitPerCustomer;
    private Integer bookingExpiryTime;
    private String imagePath;
    private String image;
    private char isWeb;
    private char isMobile;
    private String eventType;
    private List<OfferDetailResponse> offers;
    private List<ServiceDetailResponse> services;
    private String allowedUsers;
    private List<ProfileDTO> profile;
    private String status;
    private String offerAccount;
    private String link;
    private Long eventTypeId;
    private String excelFileName;
    private NtransactionDTO ntransaction;
    private String campaignMode;
    private Long customCbsQueryId;
    private String viewName;
    private String promoCodeMode;
    private Double totalOfferAmount;
}
