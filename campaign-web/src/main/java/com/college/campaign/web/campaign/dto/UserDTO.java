package com.college.campaign.web.campaign.dto;

import com.college.campaign.common.dto.ModelBase;
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
