package com.f1soft.campaign.transaction.dto;

import com.f1soft.campaign.common.dto.ModelBase;
import lombok.Getter;
import lombok.Setter;

/*
 * @Author Rashim Dhaubanjar
 */

@Getter
@Setter
public class BranchDetail extends ModelBase {

    private String fromBranchCode;
    private String toBranchCode;
    private boolean hasFromBranchCode;
    private boolean hasToBranchCode;
}
