package com.f1soft.campaign.client.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * @Author Nitesh Poudel
 */
@Getter
@Setter
public class GiftCardProviderDTO {

    private Long id;
    private Character active;
    private String code;
    private String name;

}
