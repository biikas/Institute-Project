package com.f1soft.campaign.transaction.dto.request;

import com.f1soft.campaign.common.dto.ModelBase;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author Bikash Shah
 */
@Getter
@Setter
public class CounterMerchantPaymentRequest extends ModelBase {
    private String accountNumber;
    private String merchantCode;
    private String txnPassword;
    private Long applicationUserId;
    private String username;
    private List<MerchantPaymentFieldRequest> fields;
}
