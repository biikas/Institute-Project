package com.f1soft.campaign.web.crud.systemconfig;

import com.f1soft.campaign.common.dto.ModelBase;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SystemConfigResponse extends ModelBase {
    private List<SystemConfigDTO> systemConfig;
}
