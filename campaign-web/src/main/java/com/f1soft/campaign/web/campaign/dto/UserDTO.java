package com.f1soft.campaign.web.campaign.dto;

import com.f1soft.campaign.common.dto.ModelBase;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Shreetika Panta
 */

@Getter
@Setter
public class UserDTO extends ModelBase {

    private String username;
    private String promoCode;
}
