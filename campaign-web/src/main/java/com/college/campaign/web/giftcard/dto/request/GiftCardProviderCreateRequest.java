package com.college.campaign.web.giftcard.dto.request;

import com.college.campaign.dto.ModelBase;
import lombok.Data;

/**
 * @author <krishna.pandey@college.com>
 */
@Data
public class GiftCardProviderCreateRequest extends ModelBase {

    private String code;
    private String name;
}
