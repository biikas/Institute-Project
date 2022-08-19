package com.sms.bli.request;

import lombok.Data;
import lombok.Getter;

import javax.persistence.*;
import java.util.Date;

/**
 * @author Bikash Shah
 */

@Data
public class StudentRequest {

    private String name;
    private String middleName;
    private String lastName;
    private String address;
    private String mobileNumber;
    private String gender;
    private Date dateOfBirth;
    private Date leftDate;
    private String subjects;
    private String courseId;
}
