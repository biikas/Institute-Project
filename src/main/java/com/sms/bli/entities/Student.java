package com.sms.bli.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

/**
 * @author Bikash Shah
 */

@Getter
@Setter
@Entity
@Table(name="STUDENT")
public class Student {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID")
    private Long id;
    @Column(name ="FIRST_NAME")
    private String name;
    @Column(name ="MIDDLE_NAME")
    private String middleName;
    @Column(name="LAST_NAME")
    private String lastName;
    @Column(name="ADDRESS")
    private String address;
    @Column(name="MOBILE_NUMBER")
    private String mobileNumber;
    @Column(name="GENDER")
    private String gender;
    @Column(name = "DATE_OF_BIRTH")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateOfBirth;
    @Column(name = "JOINED_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date joinedDate;
    @Column(name = "LEFT_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date leftDate;
    @Column(name="subjects")
    private String subjects;
    @Column(name="COURSE_ID")
    private String courseId;
    @Column(name="ACTIVE")
    private Character active;
}
