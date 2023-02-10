package com.f1soft.campaign.web.giftcard.dto.request;

import com.f1soft.campaign.dto.ModelBase;
import lombok.Data;

/**
 * @author <krishna.pandey@f1soft.com>
 */
@Data
public class GiftCardProviderCreateRequest extends ModelBase {

    private String code;
    private String name;
}
