package com.college.campaign.web.dto.response;

import com.college.campaign.dto.ModelBase;
import lombok.Data;

/**
 * @author <krishna.pandey@college.com>
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
