package com.f1soft.campaign.web.campaign.dto.response;

import com.f1soft.campaign.common.dto.ModelBase;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OfferDetailResponse extends ModelBase {

    private Long id;
    private String offerMode;
    private Double minAmount;
    private Double maxAmount;
    private String value;
    private String commissionType;
}
