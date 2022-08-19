package com.sms.bli.response;

import lombok.Data;

import java.util.Date;

/**
 * @author Bikash Shah
 */
@Data
public class StudentResponse {

    private String name;
    private String middleName;
    private String lastName;
    private String address;
    private String mobileNumber;
    private Date joinedDate;
    private String gender;
    private Date dateOfBirth;
    private String subjects;
    private String courseId;
}
