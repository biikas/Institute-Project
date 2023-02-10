package com.f1soft.campaign.web.campaign.dto;

import com.f1soft.campaign.common.dto.ModelBase;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Prajwol hada
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OfferCreateDTO extends ModelBase {

    private Double minAmount;
    private Double maxAmount;
    private String value;
    private String commissionType;
    private String mode;
}
