package com.f1soft.campaign.common.manager.inquiry;

import com.f1soft.campaign.common.cbs.dto.CbsQueryParameter;
import com.f1soft.campaign.common.cbs.dto.CustomerDetailDTO;
import com.f1soft.campaign.common.cbs.dto.CustomerProfileDTO;
import com.f1soft.campaign.common.cbs.dto.TransactionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @author Prajwol Hada
 */
@Component
public class EnquiryContext {

    @Autowired
    private EnquiryFactory enquiryFactory;

    public List<CustomerProfileDTO> customerProfile(CbsQueryParameter cbsQueryParameter, Map<String, Object> queryParameter) {
        return enquiryFactory.getEnquiryManager().customerProfile(cbsQueryParameter, queryParameter);
    }

    public List<CustomerDetailDTO> customerDetail(CbsQueryParameter cbsQueryParameter, Map<String, Object> queryParameter) {
        return enquiryFactory.getEnquiryManager().customerDetail(cbsQueryParameter, queryParameter);
    }

    public List<TransactionDTO> campaignWithProduct(CbsQueryParameter cbsQueryParameter, Map<String, Object> queryParameter) {
        return enquiryFactory.getEnquiryManager().campaignWithProduct(cbsQueryParameter, queryParameter);
    }

    public List<TransactionDTO> campaignWithoutProduct(CbsQueryParameter cbsQueryParameter, Map<String, Object> queryParameter) {
        return enquiryFactory.getEnquiryManager().campaignWithoutProduct(cbsQueryParameter, queryParameter);
    }
}
