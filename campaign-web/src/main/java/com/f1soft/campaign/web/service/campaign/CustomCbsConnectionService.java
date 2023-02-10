package com.f1soft.campaign.web.service.campaign;

import com.f1soft.campaign.common.dto.ServerResponse;
import com.f1soft.campaign.repository.Util.SearchQueryParameter;
import com.f1soft.campaign.web.dto.request.CustomCBSConnectionRequest;
import com.f1soft.campaign.web.dto.request.StatusRequest;

/**
 * @author Shreetika Panta
 */
public interface CustomCbsConnectionService {

    ServerResponse getAllCustomCBSQueryConnection();

    ServerResponse getAllCustomCBSConnectionList(SearchQueryParameter searchQueryParameter);

    ServerResponse getById(Long id);

    ServerResponse create(CustomCBSConnectionRequest customCBSConnectionRequest);

    ServerResponse modify(Long id, CustomCBSConnectionRequest customCBSConnectionRequest);

    ServerResponse execute(Long id);

    ServerResponse modifyStatus(Long id, StatusRequest statusRequest);
}
