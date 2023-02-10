package com.f1soft.campaign.web.campaign.dto.request.bli;

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
    private String specializedSubejcts;
    private String qualification;
    private String jobDescription;
//    private String amountToPay; //(Calculated after registration)
//    private String creditAmount; //(Amount if teachers asks money early)
    private String mobileNumber1;
    private String mobileNumber2;
}
