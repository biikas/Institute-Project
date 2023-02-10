package com.f1soft.campaign.web.mapper;

import com.f1soft.campaign.common.util.DateFormat;
import com.f1soft.campaign.common.util.DateFormatter;
import com.f1soft.campaign.dto.RegistrationReportDetail;

/**
 * @author <krishna.pandey@f1soft.com>
 */
public class RegistrationLogMapper {

    public static com.f1soft.campaign.web.dto.response.RegistrationReportDetail convertToRegistrationReportDetail(RegistrationReportDetail registrationReportDetail) {
        com.f1soft.campaign.web.dto.response.RegistrationReportDetail registrationReportDetailResponse = new com.f1soft.campaign.web.dto.response.RegistrationReportDetail();

        registrationReportDetailResponse.setRegistrationDate(DateFormatter.convertToString(registrationReportDetail.getRegistrationDate(), DateFormat.DATE_FORMAT));
        registrationReportDetailResponse.setRecordedDate(DateFormatter.convertToString(registrationReportDetail.getRecordedDate(), DateFormat.DATE_FORMAT));
        registrationReportDetailResponse.setCampaignName(registrationReportDetail.getCampaignName());
        registrationReportDetailResponse.setPromoCode(registrationReportDetail.getPromoCode());
        registrationReportDetailResponse.setUsername(registrationReportDetail.getUsername());
        registrationReportDetailResponse.setTxnAmount(registrationReportDetail.getTxnAmount());
        registrationReportDetailResponse.setOfferType(registrationReportDetail.getOfferType());
        registrationReportDetailResponse.setTxnStatus(registrationReportDetail.getTxnStatus());

        return registrationReportDetailResponse;
    }
}
