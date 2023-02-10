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
public class TransactionBookingRequest extends ModelBase {

    @NotEmpty
    private String mobileNumber;
    @NotEmpty
    private String serviceCode;
    @NotEmpty
    private String channel;
    @NotEmpty
    private String promoCode;
    @NotNull
    private Double amount;
    @NotNull
    private Long customerProfileId;
    private String emailAddress;
    private String customerName;
}
