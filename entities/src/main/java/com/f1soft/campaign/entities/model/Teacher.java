package com.f1soft.campaign.entities.model;

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
@Table(name = "TEACHER")
public class Teacher {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @Column(name = "FIRST_NAME")
    private String firstName;
    @Column(name = "MIDDLE_NAME")
    private String middleName;
    @Column(name = "LAST_NAME")
    private String lastName;
    @Column(name = "PERMANENT_ADDRESS")
    private String permanentAddress;
    @Column(name = "TEMPORARY_ADDRESS")
    private String temporaryAddress;

    //private String assignedGroup;
    @Column(name = "SPECIALIZED_SUBJECTS")
    private String specializedSubejcts;
    @Column(name = "QUALIFICATION")
    private String qualification;
    @Column(name = "JOB_DESCRIPTION")
    private String jobDescription;
    @Column(name = "AMOUNT_TO_PAY")
    private String amountToPay; //(Calculated after registration)
    @Column(name = "CREDIT_AMOUNT")
    private String creditAmount; //(Amount if teachers asks money early)
    @Column(name = "MOBILE_NUMBER1")
    private String mobileNumber1;
    @Column(name = "MOBILE_NUMBER2")
    private String mobileNumber2;
    @Column(name = "CREATED_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    @Column(name = "LAST_MODIFIED_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;
    @JoinColumn(name = "CREATED_BY", referencedColumnName = "ID", nullable = true)
    @ManyToOne()
    private ApplicationUser createdBy;
    @JoinColumn(name = "LAST_MODIFIED_BY", referencedColumnName = "ID", nullable = true)
    @ManyToOne()
    private ApplicationUser lastModifiedBy;
    @Column(name = "COMISSION_VALUE") //Commission Value in percentage
    private Double commissionValue;
    @Column(name = "EMAIL")
    private String email;
}
