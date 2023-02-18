package com.college.campaign.web.crud.systemconfig;

import com.college.campaign.common.dto.ServerResponse;
import com.college.campaign.web.dto.request.StatusRequest;

public interface SystemConfigCrudService {

    ServerResponse getSystemConfig();

    ServerResponse getSystemConfigId(Integer id);

    ServerResponse modifySystemConfig(Integer id, SystemConfigRequest systemConfigRequest);

    ServerResponse createSystemConfig(SystemConfigRequest systemConfigRequest);

    ServerResponse modifyStatus(Integer id, StatusRequest statusRequest);
}
