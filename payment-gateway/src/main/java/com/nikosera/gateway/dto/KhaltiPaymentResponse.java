package com.nikosera.gateway.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * @author narayanjoshi
 * @email <narayan.joshi>
 * Created Date: 12/12/22
 */

@Getter
@Setter
public class KhaltiPaymentResponse {

    private String pidx;
    private String payment_url;

}
