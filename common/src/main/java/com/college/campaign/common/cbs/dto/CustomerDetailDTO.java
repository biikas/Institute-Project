package com.college.campaign.common.cbs.dto;

import com.college.campaign.common.dto.ModelBase;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @author Shreetika Panta
 */

@Getter
@Setter
public class CustomerDetailDTO extends ModelBase {

    private Long id;
    private String username;
    private String accountNumber;
    private Date registrationDate;
    private Long profileId;
    private String accountType;
    private String channel;
    private String customerName;

}
