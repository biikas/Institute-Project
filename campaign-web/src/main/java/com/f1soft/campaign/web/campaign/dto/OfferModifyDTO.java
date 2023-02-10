package com.f1soft.campaign.web.campaign.dto;

import com.f1soft.campaign.common.dto.ModelBase;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Prajwol hada
 */
@Getter
@Setter
public class OfferModifyDTO extends ModelBase {

    private Long id;
    private Double minAmount;
    private Double maxAmount;
    private String value;
    private String commissionType;
    private String mode;
}
