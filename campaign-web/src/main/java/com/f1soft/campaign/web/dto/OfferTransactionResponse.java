package com.f1soft.campaign.web.dto;

import com.f1soft.campaign.common.dto.ModelBase;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OfferTransactionResponse extends ModelBase {
    private String eid;
    private String mobileNumber;
    private String claimDate;
    private Double amount;
    private String type;
    private String offerTxnCode;
    private String txnCode;
    private String status;
    private String channel;
    private String offerType;
    private String txnDate;
    private String remarks;
    private String txnStatus;
    private String campaignName;
    private String campaignMode;
    private Double txnAmount;
    private String customerName;
}
