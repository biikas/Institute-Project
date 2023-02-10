package com.f1soft.campaign.web.dto.response;

import com.f1soft.campaign.common.dto.ModelBase;
import lombok.Data;

/**
 * @author <krishna.pandey@f1soft.com>
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
