package com.f1soft.campaign.entities.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author Prajwol hada
 */
@Getter
@Setter
@Entity
@Table(name = "APPLICATION_USER")
public class ApplicationUser implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID", nullable = false, precision = 22)
    private Long id;
    @Basic(optional = false)
    @Column(name = "PASSWORD", nullable = false, length = 200)
    private String password;
    @Basic(optional = false)
    @Column(name = "USERNAME", nullable = false, length = 50)
    private String username;
    @Column(name = "MOBILE_NUMBER", length = 20)
    private String mobileNumber;
    @Basic(optional = false)
    @Column(name = "NAME", nullable = false, length = 200)
    private String name;
    @Column(name = "EMAIL_ADDRESS", nullable = true, length = 200)
    private String emailAddress;
    @Basic(optional = false)
    @Column(name = "ACTIVE", nullable = false)
    private char active;
    @Basic(optional = false)
    @Column(name = "CREATED_DATE", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    @JoinColumn(name = "CREATED_BY", referencedColumnName = "ID", nullable = true)
    @ManyToOne()
    private ApplicationUser createdBy;
    @Column(name = "LAST_MODIFIED_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;
    @JoinColumn(name = "LAST_MODIFIED_BY", referencedColumnName = "ID", nullable = true)
    @ManyToOne()
    private ApplicationUser lastModifiedBy;
    @Column(name = "LAST_PASSWORD_CHANGED", nullable = true)
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastPasswordChanged;
    @Column(name = "ADMIN_TYPE", length = 50, nullable = false)
    private String adminTypeName;
    @JoinColumn(name = "ADMIN_TYPE_ID", referencedColumnName = "ID")
    @ManyToOne()
    private AdminType adminType;
}
