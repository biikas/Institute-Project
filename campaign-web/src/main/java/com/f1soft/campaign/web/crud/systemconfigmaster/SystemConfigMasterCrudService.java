package com.f1soft.campaign.web.crud.systemconfigmaster;

import com.f1soft.campaign.common.dto.ServerResponse;
import com.f1soft.campaign.web.dto.request.StatusRequest;

public interface SystemConfigMasterCrudService {

    ServerResponse createSystemConfigMaster(SystemConfigMasterRequest systemConfigMasterRequest);

    ServerResponse getSystemConfigMaster();

    ServerResponse getSystemConfigMasterById(Integer id);

    ServerResponse modifySystemConfigMaster(Integer id, SystemConfigMasterRequest systemConfigMasterRequest);

    ServerResponse modifyStatus(Integer id, StatusRequest statusRequest);
}
