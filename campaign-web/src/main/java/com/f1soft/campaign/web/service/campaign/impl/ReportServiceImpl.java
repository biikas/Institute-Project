package com.f1soft.campaign.web.service.campaign.impl;

import com.f1soft.campaign.common.constant.MsgConstant;
import com.f1soft.campaign.common.dto.PageResponse;
import com.f1soft.campaign.common.dto.ServerResponse;
import com.f1soft.campaign.common.util.ResponseMsg;
import com.f1soft.campaign.dto.RegistrationReportDetail;
import com.f1soft.campaign.repository.Util.FieldQueryParameter;
import com.f1soft.campaign.repository.Util.SearchQueryParameter;
import com.f1soft.campaign.repository.nativequery.CustomerRegistrationNative;
import com.f1soft.campaign.web.campaign.helper.ReportHelper;
import com.f1soft.campaign.web.mapper.RegistrationLogMapper;
import com.f1soft.campaign.web.service.campaign.ReportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author <krishna.pandey@f1soft.com>
 */
@Slf4j
@Service
public class ReportServiceImpl implements ReportService {
    @Autowired
    private CustomerRegistrationNative customerRegistrationNative;

    @Override
    public ServerResponse searchReport(SearchQueryParameter searchQueryParameter) {
        List<FieldQueryParameter> fieldQueryParameterList = ReportHelper.getQueryParameterListForReportFilter(searchQueryParameter);

        List<RegistrationReportDetail> reportDetails = customerRegistrationNative.fetchRegistrationCampaignReport(fieldQueryParameterList, PageRequest.of(searchQueryParameter.getPage(), searchQueryParameter.getSize()));
        long count = customerRegistrationNative.fetchRegistrationCampaignReportCount(searchQueryParameter.getSearch());
        log.debug("registration report total count : {}", count);

        List<com.f1soft.campaign.web.dto.response.RegistrationReportDetail> registrationResponse = reportDetails.stream()
                .map(RegistrationLogMapper::convertToRegistrationReportDetail).collect(Collectors.toList());

        PageResponse pageResponse = new PageResponse(registrationResponse, count);

        return ResponseMsg.successResponse(MsgConstant.Data.SUCCESS, pageResponse);
    }
}
