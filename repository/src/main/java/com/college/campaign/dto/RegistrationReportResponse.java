package com.college.campaign.dto;

import lombok.Data;

import java.util.List;

/**
 * @author <krishna.pandey@college.com>
 */
@Data
public class RegistrationReportResponse extends ModelBase {

    private List<RegistrationReportDetail> registrationReports;
}
