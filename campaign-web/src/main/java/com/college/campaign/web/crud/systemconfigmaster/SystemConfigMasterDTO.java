package com.college.campaign.web.crud.systemconfigmaster;

import com.college.campaign.common.dto.ModelBase;
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
