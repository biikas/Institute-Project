package com.college.campaign.web.dto.response;

import com.college.campaign.dto.ModelBase;
import lombok.Data;

/**
 * @author <krishna.pandey@college.com>
 */

@Data
public class GiftCardProviderDetail extends ModelBase {

    private Long id;
    private Character active;
    private String code;
    private String name;
}
