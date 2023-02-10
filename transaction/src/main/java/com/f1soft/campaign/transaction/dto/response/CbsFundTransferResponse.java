package com.f1soft.campaign.transaction.dto.response;

import com.f1soft.campaign.common.dto.ModelBase;
import lombok.Getter;
import lombok.Setter;

/*
 * @Author Rashim Dhaubanjar
 */

@Getter
@Setter
public class CbsFundTransferResponse extends ModelBase {

    private String requestId;
    private String stanId;
    private String respCode;
    private String respDesc;
    private String benificiaryName;
}
