package com.f1soft.campaign.common.manager.inquiry;


import com.f1soft.campaign.common.cbs.dto.*;

import java.util.List;
import java.util.Map;

/**
 * @author Shreetika Panta
 */
public interface CustomEnquiryManager {

    List<CustomRedeemDTO> customRedeemDetail(CbsQueryParameter cbsQueryParameter, Map<String, Object> queryParameter);
}
