package com.college.campaign.web.crud.systemconfigmaster;

import com.college.campaign.common.dto.ServerResponse;
import com.college.campaign.web.dto.request.StatusRequest;

public interface SystemConfigMasterCrudService {

    ServerResponse createSystemConfigMaster(SystemConfigMasterRequest systemConfigMasterRequest);

    ServerResponse getSystemConfigMaster();

    ServerResponse getSystemConfigMasterById(Integer id);

    ServerResponse modifySystemConfigMaster(Integer id, SystemConfigMasterRequest systemConfigMasterRequest);

    ServerResponse modifyStatus(Integer id, StatusRequest statusRequest);
}
