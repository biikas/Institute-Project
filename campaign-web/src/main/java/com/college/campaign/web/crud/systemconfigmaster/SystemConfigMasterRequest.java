package com.college.campaign.web.crud.systemconfigmaster;

import com.college.campaign.common.dto.ModelBase;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class SystemConfigMasterRequest extends ModelBase {

    private String name;
    private String code;
    private char active;
}
