package com.college.campaign.web.campaign.dto.response;

import com.college.campaign.common.dto.ModelBase;
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
