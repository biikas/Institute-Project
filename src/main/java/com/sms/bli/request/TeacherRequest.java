package com.sms.bli.request;

import lombok.Data;

import java.util.Date;

/**
 * @author Bikash Shah
 */

@Data
public class TeacherRequest {

    private String name;
    private String middleName;
    private String lastName;
    private String address;
    private String mobileNumber;
    private String gender;
    private Date dateOfBirth;
    private String subjects;
    private String courseId;
    private String specifications;

}
