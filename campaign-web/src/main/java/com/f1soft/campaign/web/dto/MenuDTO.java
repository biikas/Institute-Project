package com.f1soft.campaign.web.dto;

import com.f1soft.campaign.common.dto.ModelBase;
import lombok.Getter;
import lombok.Setter;

/**
 * @Author Nitesh Poudel
 */
@Getter
@Setter
public class MenuDTO extends ModelBase {

    private Long id;
    private String name;
    private String description;
    private String code;
    private String adminMenuGroupName;

}
