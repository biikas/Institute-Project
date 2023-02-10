package com.f1soft.campaign.dto;

import lombok.Data;

import java.util.Date;

/**
 * @author <krishna.pandey@f1soft.com>
 */
@Data
public class RegistrationReportDetail extends ModelBase {

    private String username;
    private Date recordedDate;
    private Date registrationDate;
    private String promoCode;
    private String campaignName;
    private Double txnAmount;
    private String offerType;
    private String txnStatus;
}
