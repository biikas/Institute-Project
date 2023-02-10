package com.f1soft.campaign.transaction.dto.response;

import com.f1soft.campaign.common.dto.ModelBase;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FundTransferResponse extends ModelBase {
    private String stanId;
    private boolean transferred;
    private String errorCode;
    private CommissionResponse commission;
    private Double txnAmount;
    private String message;
    private String benificiaryName;
    private String requestTraceId;
}
