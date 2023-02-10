package com.f1soft.campaign.web.crud.systemconfig;

import com.f1soft.campaign.common.dto.ServerResponse;
import com.f1soft.campaign.web.dto.request.StatusRequest;

public interface SystemConfigCrudService {

    ServerResponse getSystemConfig();

    ServerResponse getSystemConfigId(Integer id);

    ServerResponse modifySystemConfig(Integer id, SystemConfigRequest systemConfigRequest);

    ServerResponse createSystemConfig(SystemConfigRequest systemConfigRequest);

    ServerResponse modifyStatus(Integer id, StatusRequest statusRequest);
}
