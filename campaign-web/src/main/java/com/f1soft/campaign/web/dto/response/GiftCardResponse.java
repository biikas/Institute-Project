package com.f1soft.campaign.web.dto.response;

import com.f1soft.campaign.dto.ModelBase;
import lombok.Data;

/**
 * @author <krishna.pandey@f1soft.com>
 */

@Data
public class GiftCardResponse extends ModelBase {

    private Long id;
    private GiftCardProviderDetail giftCardProvider;
    private Character active;
    private String code;
    private String name;
    private String expiryDate;
    private String amount;
}
