package com.nikosera.gateway.dto;

import com.nikosera.common.dto.ModelBase;
import lombok.Getter;
import lombok.Setter;

/**
 * @author narayanjoshi
 * @email <narayan.joshi>
 * Created Date: 12/6/22
 */

@Getter
@Setter
public class PaymentRequestDto extends ModelBase {

    private Double amount;
    private String orderId;
    private String orderName;
    private String initiatorName;
    private String initiatorEmail;
    private String initiatorMobileNumber;

}
