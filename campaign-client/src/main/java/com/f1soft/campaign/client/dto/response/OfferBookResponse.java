package com.f1soft.campaign.client.dto.response;

import com.f1soft.campaign.common.dto.ModelBase;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Prajwol hada
 */
@Getter
@Setter
public class OfferBookResponse extends ModelBase {

    private String expiryTime;
    private String policy;
    private Double cashback;
    private String offerBookingId;
}
