package com.f1soft.campaign.web.dto;

import com.f1soft.campaign.common.dto.ModelBase;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OfferModeResponse extends ModelBase {

    private Long id;
    private String code;
    private String name;
    private List<OfferCommissionTypeResponse> offerCommissionTypes;
    private List<PackageResponse> packageOptions;
}
