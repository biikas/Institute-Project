package com.f1soft.campaign.web.dto;

import com.f1soft.campaign.common.dto.ModelBase;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Shreetika Panta
 */

@Getter
@Setter
public class OfferCommissionTypeResponse extends ModelBase {

    private Long id;
    private String code;
    private String name;
}
