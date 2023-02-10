package com.f1soft.campaign.web.dto.response;

import com.f1soft.campaign.dto.ModelBase;
import lombok.Data;

/**
 * @author <krishna.pandey@f1soft.com>
 */

@Data
public class GiftCardProviderDetail extends ModelBase {

    private Long id;
    private Character active;
    private String code;
    private String name;
}
