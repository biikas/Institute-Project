package com.college.campaign.entities.model;

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
@Table(name = "Contact")
public class Contact {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID", nullable = false)
    private Long id;
    @Column(name="FIRST_NAME")
    private String firstName;
    @Column(name = "lastName")
    private String lastName;
    @Column(name= "MOBILE_NUMBER")
    private String mobileNumber;
    @Column(name= "EMAIL")
    private String email;
    @Column(name ="Active")
    private String active;
    @Column(name = "DATE_OF_BIRTH", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateOfBirth;

}
