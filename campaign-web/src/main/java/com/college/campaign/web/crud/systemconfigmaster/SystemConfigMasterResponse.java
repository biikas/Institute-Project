package com.college.campaign.web.crud.systemconfigmaster;


import com.college.campaign.common.dto.ModelBase;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SystemConfigMasterResponse extends ModelBase {
    private List<SystemConfigMasterDTO> systemConfigMaster;
}
