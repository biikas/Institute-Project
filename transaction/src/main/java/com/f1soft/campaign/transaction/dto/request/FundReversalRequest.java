package com.f1soft.campaign.transaction.dto.request;

import com.f1soft.campaign.common.dto.ModelBase;
import lombok.Getter;
import lombok.Setter;

/*
 * @Author Rashim Dhaubanjar
 */

@Getter
@Setter
public class FundReversalRequest extends ModelBase {

    private String requestId;
    private String remarks;

}
