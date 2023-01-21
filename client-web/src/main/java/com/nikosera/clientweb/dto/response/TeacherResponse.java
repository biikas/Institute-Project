package com.nikosera.clientweb.dto.response;

import lombok.Data;

import java.util.Date;

/**
 * @author Bikash Shah
 */
@Data
public class TeacherResponse {
    private String name;
    private String middleName;
    private String lastName;
    private String address;
    private String mobileNumber;
    private String gender;
    private Date dateOfBirth;
    private Date joinedDate;
    private Date leftDate;
    private String subjects;
    private String courseId;
    private String specifications;
}
