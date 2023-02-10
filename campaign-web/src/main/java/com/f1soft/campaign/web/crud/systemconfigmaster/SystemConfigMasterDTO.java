package com.f1soft.campaign.web.crud.systemconfigmaster;

import com.f1soft.campaign.common.dto.ModelBase;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SystemConfigMasterDTO extends ModelBase {

    private Integer id;
    private String name;
    private String code;
    private String createdDate;
    private char active;
}
