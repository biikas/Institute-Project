package com.f1soft.campaign.web.campaign.dto;

import com.f1soft.campaign.common.dto.ModelBase;
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
