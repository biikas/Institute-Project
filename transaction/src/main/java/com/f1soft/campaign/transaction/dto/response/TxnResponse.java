package com.f1soft.campaign.transaction.dto.response;

import com.f1soft.campaign.common.dto.ModelBase;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TxnResponse extends ModelBase {
    private boolean transferred;
    private String errorCode;
    private String errorMSG;
    private Double requestedAmount = 0.0;
    private Double transferedAmount = 0.0;
    private Double commissionAmount = 0.0;
    private String commissionPayee;
    private Long txnRefId;
    private Long requestInfoId;
    private String benificiaryName;

    public Double getCommissionAmount() {
        if (commissionAmount == null) {
            commissionAmount = 0.00;
        }
        return commissionAmount;
    }
}
