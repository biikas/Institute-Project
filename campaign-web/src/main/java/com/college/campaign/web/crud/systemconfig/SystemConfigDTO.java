package com.college.campaign.web.crud.systemconfig;

import com.college.campaign.common.dto.ModelBase;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SystemConfigDTO extends ModelBase {

    private Long id;
    private String paramCode;
    private String paramValue;
    private Integer systemConfigMasterId;
    private String allowedValue;
    private String createdDate;
    private char active;
}
