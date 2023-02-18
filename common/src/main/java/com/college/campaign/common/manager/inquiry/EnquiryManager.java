package com.college.campaign.common.manager.inquiry;


import com.college.campaign.common.cbs.dto.CbsQueryParameter;
import com.college.campaign.common.cbs.dto.CustomerDetailDTO;
import com.college.campaign.common.cbs.dto.CustomerProfileDTO;
import com.college.campaign.common.cbs.dto.TransactionDTO;

import java.util.List;
import java.util.Map;

/**
 * @author Prajwol Hada
 */
public interface EnquiryManager {

    List<CustomerProfileDTO> customerProfile(CbsQueryParameter cbsQueryParameter, Map<String, Object> queryParameterMap);

    List<CustomerDetailDTO> customerDetail(CbsQueryParameter cbsQueryParameter, Map<String, Object> queryParameter);

    List<TransactionDTO> campaignWithProduct(CbsQueryParameter cbsQueryParameter, Map<String, Object> queryParameter);

    List<TransactionDTO> campaignWithoutProduct(CbsQueryParameter cbsQueryParameter, Map<String, Object> queryParameter);
}
