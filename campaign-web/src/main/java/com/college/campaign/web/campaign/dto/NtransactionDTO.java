package com.college.campaign.web.campaign.dto;

import com.college.campaign.common.dto.ModelBase;
import lombok.Getter;
import lombok.Setter;


/**
 * @author Bikash Shah
 */
@Getter
@Setter
public class NtransactionDTO extends ModelBase {
    private Double minimumAmount;
    private Long noOfTransaction;

}
