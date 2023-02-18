package com.college.campaign.web.bli.dto;

import lombok.Getter;
import lombok.Setter;


/**
 * @author Bikash Shah
 */
@Getter
@Setter
public class TeacherCreateRequest {

    private String firstName;
    private String middleName;
    private String lastName;
    private String permanentAddress;

    //private String assignedGroup;
    private String specializedSubjects;
    private String qualification;
    private String jobDescription;
//    private String amountToPay; //(Calculated after registration)
//    private String creditAmount; //(Amount if teachers asks money early)
    private String mobileNumber1;
    private String mobileNumber2;
    private Double commissionValue;
    private Double monthlySalary;
    private String assignedGroupId;
    private String email;
    private String temporaryAddress;
}
