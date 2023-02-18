package com.college.campaign.web.dto.response;

import com.college.campaign.common.dto.ModelBase;
import lombok.Data;

/**
 * @author <krishna.pandey@college.com>
 */
@Data
public class RegistrationReportDetail extends ModelBase {

    private String username;
    private String recordedDate;
    private String registrationDate;
    private String promoCode;
    private String campaignName;
    private Double txnAmount;
    private String offerType;
    private String txnStatus;
}
