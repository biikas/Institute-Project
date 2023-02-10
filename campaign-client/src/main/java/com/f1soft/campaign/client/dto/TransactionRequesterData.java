package com.f1soft.campaign.client.dto;

import com.f1soft.campaign.common.dto.ModelBase;
import com.f1soft.campaign.entities.model.BookingRequest;
import com.f1soft.campaign.entities.model.Campaign;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Prajwol hada
 */
@Getter
@Setter
@Builder
public class TransactionRequesterData extends ModelBase {

    private String mobileNumber;
    private String serviceCode;
    private String channel;
    private Double transactionAmount;
    private Campaign campaign;
    private String accountNumber;
    private String bookingId;
    private Long transactionId;
    private Long profileId;
    private BookingRequest bookingRequest;
    private String emailAddress;
    private String customerName;

}
