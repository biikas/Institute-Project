package com.f1soft.campaign.web.bli.dto;

import lombok.Data;

/**
 * @author Bikash Shah
 */
@Data
public class TeacherResponse {

    private String firstName;
    private String middleName;
    private String lastName;
    private String permanentAddress;
    private String specializedSubjects;
    private String qualification;
    private String jobDescription;
    private String mobileNumber1;
    private String mobileNumber2;
    private Double commissionValue;
    private Double monthlySalary;
    private String assignedGroupId;
    private String email;
    private String temporaryAddress;
//    private String createdBy;
//    private String createdDate;
//    private String lastModifiedBy;
//    private String lastModifiedDate;
}
