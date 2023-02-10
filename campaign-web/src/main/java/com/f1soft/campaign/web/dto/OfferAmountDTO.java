package com.f1soft.campaign.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Prajwol Hada
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OfferAmountDTO {

    private Double minAmount;
    private Double maxAmount;
}
