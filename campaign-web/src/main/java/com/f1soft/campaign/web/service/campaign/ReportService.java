package com.f1soft.campaign.web.service.campaign;

import com.f1soft.campaign.common.dto.ServerResponse;
import com.f1soft.campaign.repository.Util.SearchQueryParameter;

/**
 * @author <krishna.pandey@f1soft.com>
 */
public interface ReportService {

    ServerResponse searchReport(SearchQueryParameter searchQueryParameter);
}
