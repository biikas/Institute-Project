package com.f1soft.campaign.web.dto;

import com.f1soft.campaign.common.dto.ModelBase;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransactionDetailResponse extends ModelBase {

    private Long id;
    private Long transactionId;
    private String mobileNumber;
    private String recordedDate;
    private String transactionDate;
    private String promoCode;
    private Double amount;
    private Double txnAmount;
    private String status;
    private String remarks;
    private String serviceName;
    private String accountNumber;
}
