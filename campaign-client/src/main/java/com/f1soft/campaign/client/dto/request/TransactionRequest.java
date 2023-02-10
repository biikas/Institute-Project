package com.f1soft.campaign.client.dto.request;

import com.f1soft.campaign.common.dto.ModelBase;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author Prajwol hada
 */
@Getter
@Setter
public class TransactionRequest extends ModelBase {
    @NotEmpty
    private String bookingId;
    @NotEmpty
    private String accountNumber;
    @NotNull
    private long transactionId;
    private Double amount;
}
