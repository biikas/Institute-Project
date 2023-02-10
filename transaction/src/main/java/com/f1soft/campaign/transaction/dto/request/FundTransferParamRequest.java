package com.f1soft.campaign.transaction.dto.request;

import com.f1soft.campaign.common.dto.ModelBase;
import com.f1soft.campaign.transaction.dto.BranchDetail;
import com.f1soft.campaign.transaction.dto.IsoField;
import com.f1soft.campaign.transaction.dto.Partner;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/*
 * @Author Rashim Dhaubanjar
 */

@Getter
@Setter
public class FundTransferParamRequest extends ModelBase {

    private String fromAccount;
    private String toAccount;
    private Double amount;
    private String currencyCode;
    private List<IsoField> fields;
    private String requestId;
    private List<Partner> partners;
    private String initiator;
    private BranchDetail branch;
    private String serviceCode;
    private String transactionId;

}
