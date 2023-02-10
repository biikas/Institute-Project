package com.f1soft.campaign.client.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * @Author Nitesh Poudel
 */
@Getter
@Setter
public class GiftCardDTO {

    private Long id;
    private GiftCardProviderDTO giftCardProvider;
    private Character active;
    private String code;
    private String name;
    private String expiryDate;
    private Double amount;
}
