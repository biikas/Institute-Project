package com.f1soft.campaign.common.cbs.dto;

import com.f1soft.campaign.common.dto.ModelBase;
import lombok.Getter;
import lombok.Setter;
import org.joda.time.DateTime;

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
