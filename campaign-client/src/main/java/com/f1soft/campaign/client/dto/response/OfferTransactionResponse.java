package com.f1soft.campaign.client.dto.response;

import com.f1soft.campaign.common.dto.ModelBase;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OfferTransactionResponse extends ModelBase {

    private Double txnAmount;
    private Double offerAmount;
    private String offerModeName;
    private String offerModeCode;
    private String promoCode;
}
