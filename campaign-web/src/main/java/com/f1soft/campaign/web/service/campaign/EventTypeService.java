package com.f1soft.campaign.web.service.campaign;

import com.f1soft.campaign.common.dto.ServerResponse;

public interface EventTypeService {

    ServerResponse getEventTypes();

    ServerResponse eventTypeCustomView();
}
