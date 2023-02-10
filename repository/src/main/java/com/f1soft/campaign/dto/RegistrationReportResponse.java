package com.f1soft.campaign.dto;

import lombok.Data;

import java.util.List;

/**
 * @author <krishna.pandey@f1soft.com>
 */
@Data
public class RegistrationReportResponse extends ModelBase {

    private List<RegistrationReportDetail> registrationReports;
}
