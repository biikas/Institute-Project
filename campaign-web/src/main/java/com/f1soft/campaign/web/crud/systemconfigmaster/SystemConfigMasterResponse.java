package com.f1soft.campaign.web.crud.systemconfigmaster;


import com.f1soft.campaign.common.dto.ModelBase;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SystemConfigMasterResponse extends ModelBase {
    private List<SystemConfigMasterDTO> systemConfigMaster;
}
