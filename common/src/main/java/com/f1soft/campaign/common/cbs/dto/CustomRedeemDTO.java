package com.f1soft.campaign.common.cbs.dto;

import com.f1soft.campaign.common.dto.ModelBase;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @author Shreetika Panta
 */

@Getter
@Setter
public class CustomRedeemDTO extends ModelBase {

    private String mobileNumber;
    private Date recordedDate;
    private String channel;
    private String accountNumber;
    private Long id;
}
