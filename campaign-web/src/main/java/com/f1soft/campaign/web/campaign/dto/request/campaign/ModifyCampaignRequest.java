package com.f1soft.campaign.web.campaign.dto.request.campaign;

import com.f1soft.campaign.common.dto.ModelBase;
import com.f1soft.campaign.web.campaign.dto.OfferModifyDTO;
import com.f1soft.campaign.web.campaign.dto.response.ModifyServiceDTO;
import com.f1soft.campaign.web.dto.ProfileDTO;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
public class ModifyCampaignRequest extends ModelBase {

    @NotNull
    private Long id;
    @NotEmpty
    private String title;
    @NotEmpty
    private String shortDescription;
    @NotEmpty
    private String description;
    private String promoCode;
    @NotEmpty
    private String startDate;
    @NotEmpty
    private String endDate;
    @NotEmpty
    private String policy;
    @NotNull
    private int totalIssued;
    @NotNull
    private int usagePerCustomer;
    @NotNull
    private int expiryTime;
    private String eventType;
    @NotNull
    private char isWeb;
    @NotNull
    private char isMobile;
    private String image;
    private List<OfferModifyDTO> offers;
    private List<ModifyServiceDTO> services;
    private List<ProfileDTO> profiles;
    private String allowedUsers;
    @NotEmpty
    private String offerAccount;
    private String link;
    private Long eventTypeId;
    private String campaignMode;
    private Integer customCbsQueryId;
    @NotNull
    private Double totalOfferAmount;
}
