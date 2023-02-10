package com.f1soft.campaign.transaction.dto.response;

import com.f1soft.campaign.common.dto.ModelBase;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.List;

@Getter
@Setter
public class CommissionResponse extends ModelBase {
    private boolean hasCommission;
    private Double mainTransactionAmount;
    private Double commission = 0.0;
    private Double commissionToShow = 0.0;
    private Double paidAmount;
    private String commissionRate;
    private char commissionType;
//    private CommissionSharing commissinSharing;
    private Long request;
    private Long cardRequest;
    private HashMap<String, Double> commissionPartnersAmount;
    private HashMap<String, Double> commissionPartnersInISO;
    private List<CommissionResponse> partnerResponses;
    private String commissionPayee;
    private Double amountPlusCommission;
    private Double amountMinusCommission;
    private String partnerAccount;
    private boolean initiateTxn;
    private String remarks;
    private char realTimeSettled;
}
