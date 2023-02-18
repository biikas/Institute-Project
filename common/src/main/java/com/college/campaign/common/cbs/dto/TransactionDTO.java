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
public class TransactionDTO extends ModelBase {

    private Long id;
    private String username;
    private String accountNumber;
    private String transactionCode;
    private Date transactionDate;
    private Long profileId;
    private Double amount;
    private String channel;
    private String customerName;
}
