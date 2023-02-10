package com.f1soft.campaign.web.crud.service;

import com.f1soft.campaign.common.dto.ServerResponse;
import com.f1soft.campaign.web.dto.request.StatusRequest;

public interface ServiceCrudService {

    ServerResponse getService();

    ServerResponse getServiceById(Long id);

    ServerResponse modifyService(Long id, ServiceRequest serviceRequest);

    ServerResponse createService(ServiceRequest serviceRequest);

    ServerResponse modifyStatus(Long id, StatusRequest statusRequest);
}
