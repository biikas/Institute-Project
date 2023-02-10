package com.f1soft.campaign.common.manager;

import com.f1soft.campaign.common.cbs.dto.CbsQueryParameter;
import com.f1soft.campaign.common.cbs.dto.CustomerDetailDTO;
import com.f1soft.campaign.common.cbs.dto.CustomerProfileDTO;
import com.f1soft.campaign.common.cbs.dto.TransactionDTO;
import com.f1soft.campaign.common.constant.CbsQueryConstant;
import com.f1soft.campaign.common.manager.inquiry.EnquiryContext;
import com.f1soft.campaign.common.mapper.CbsQueryParameterMapper;
import com.f1soft.campaign.entities.model.CBSQuery;
import com.f1soft.campaign.entities.model.Campaign;
import com.f1soft.campaign.entities.model.CampaignEligibleService;
import com.f1soft.campaign.repository.CBSQueryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Sabrin Luitel
 */
@Slf4j
@Component
public class QueryManager {

    @Autowired
    private EnquiryContext enquiryContext;
    @Autowired
    private CBSQueryRepository cbsQueryRepository;

    public List<CustomerProfileDTO> customerProfile() {
        CBSQuery cbsQuery = cbsQueryRepository.findByCode(CbsQueryConstant.CUSTOMER_PROFILE).get();
        CbsQueryParameter cbsQueryParameter = CbsQueryParameterMapper.convertToCbsQueryParameter(cbsQuery);
        Map<String, Object> queryParameter = new HashMap<>();
        return enquiryContext.customerProfile(cbsQueryParameter, queryParameter);
    }

    public List<CustomerDetailDTO> getRegisteredCustomerDetail(Campaign campaign, Long trackId) {
        CBSQuery cbsQuery = cbsQueryRepository.findByCode(CbsQueryConstant.CUSTOMER_DETAIL).get();
        CbsQueryParameter cbsQueryParameter = CbsQueryParameterMapper.convertToCbsQueryParameter(cbsQuery);
        Map<String, Object> queryParameter = new HashMap<>();
        queryParameter.put("trackId", String.valueOf(trackId));
        queryParameter.put("startDate", String.valueOf(campaign.getStartDate()));
        queryParameter.put("endDate", String.valueOf(campaign.getEndDate()));
        return enquiryContext.customerDetail(cbsQueryParameter, queryParameter);
    }

    public List<TransactionDTO> getProcessCampaignWithProduct(Campaign campaign, CampaignEligibleService campaignEligibleService) {
        CBSQuery cbsQuery = cbsQueryRepository.findByCode(CbsQueryConstant.CAMPAIGN_TXN_DETAIL_WITH_PRODUCT).get();
        CbsQueryParameter cbsQueryParameter = CbsQueryParameterMapper.convertToCbsQueryParameter(cbsQuery);
        Map<String, Object> queryParameter = new HashMap<>();
        queryParameter.put("serviceCode", String.valueOf(campaignEligibleService.getServiceCode()));
        queryParameter.put("startDate", String.valueOf(campaign.getStartDate()));
        queryParameter.put("endDate", String.valueOf(campaign.getEndDate()));
        queryParameter.put("limit", String.valueOf(campaign.getEventAttribute().getCount()));
        queryParameter.put("minAmount", String.valueOf(campaign.getEventAttribute().getMinimumAmount()));
        return enquiryContext.campaignWithProduct(cbsQueryParameter, queryParameter);
    }

    public List<TransactionDTO> getProcessCampaignWithoutProduct(Campaign campaign) {
        CBSQuery cbsQuery = cbsQueryRepository.findByCode(CbsQueryConstant.CAMPAIGN_TXN_DETAIL_WITHOUT_PRODUCT).get();
        CbsQueryParameter cbsQueryParameter = CbsQueryParameterMapper.convertToCbsQueryParameter(cbsQuery);
        Map<String, Object> queryParameter = new HashMap<>();
        queryParameter.put("startDate", String.valueOf(campaign.getStartDate()));
        queryParameter.put("endDate", String.valueOf(campaign.getEndDate()));
        queryParameter.put("limit", String.valueOf(campaign.getEventAttribute().getCount()));
        queryParameter.put("minAmount", String.valueOf(campaign.getEventAttribute().getMinimumAmount()));
        return enquiryContext.campaignWithoutProduct(cbsQueryParameter, queryParameter);
    }
}
