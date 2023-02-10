package com.f1soft.campaign.transaction.dto.request;

import com.f1soft.campaign.dto.ModelBase;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Bikash Shah
 */
@Getter
@Setter
public class MerchantPaymentFieldRequest extends ModelBase {

    private Integer paramOrder;
    private String paramValue;
    private String label;
}
