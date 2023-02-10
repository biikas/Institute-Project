package com.f1soft.campaign.common.manager.inquiry;

import com.f1soft.campaign.common.cbs.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @author Prajwol Hada
 */
@Component
public class CustomEnquiryContext {

    @Autowired
    private CustomEnquiryFactory enquiryFactory;

    public List<CustomRedeemDTO> customRedeemDetail(CbsQueryParameter cbsQueryParameter, Map<String, Object> queryParameter) {
        return enquiryFactory.getEnquiryManager().customRedeemDetail(cbsQueryParameter, queryParameter);
    }
}
