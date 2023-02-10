package com.f1soft.campaign.transaction.dto;

import com.f1soft.campaign.entities.model.ApplicationUser;
import com.f1soft.campaign.entities.model.Campaign;
import com.f1soft.campaign.entities.model.OfferTransaction;
import lombok.*;

/**
 * @author Prajwol Hada
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RedeemRequestData {

    private OfferTransaction offerTransaction;
    private Campaign campaign;
    private String accountNumber;
    private String toAccount;
    private Double amount;
    private String remarks;
    private ApplicationUser applicationUser;
}
