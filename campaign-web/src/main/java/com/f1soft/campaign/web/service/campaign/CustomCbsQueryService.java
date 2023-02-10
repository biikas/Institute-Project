package com.f1soft.campaign.web.service.campaign;

import com.f1soft.campaign.common.dto.ServerResponse;
import com.f1soft.campaign.repository.Util.SearchQueryParameter;
import com.f1soft.campaign.web.dto.request.CustomCbsQueryRequest;
import com.f1soft.campaign.web.dto.request.StatusRequest;

/**
 * @author Shreetika Panta
 */
public interface CustomCbsQueryService {

    ServerResponse getAllCustomCBSQueryList(SearchQueryParameter searchQueryParameter);

    ServerResponse execute(Long id);

    ServerResponse getById(Long id);

    ServerResponse create(CustomCbsQueryRequest cbsQueryRequest);

    ServerResponse modify(Long eid, CustomCbsQueryRequest cbsQueryRequest);

    ServerResponse modifyStatus(Long id, StatusRequest statusRequest);
}
