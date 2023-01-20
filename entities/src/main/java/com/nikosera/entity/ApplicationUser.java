package com.nikosera.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author Narayan Joshi
 * <narenzoshi@gmail.com>
 */
@Getter
@Setter
@Entity(name = "APPLICATION_USER")
public class ApplicationUser implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false, precision = 22)
    private Long id;

    @JsonIgnore
    @Column(name = "PASSWORD", length = 200)
    private String password;

    @Column(name = "USERNAME", nullable = false, length = 50)
    private String username;

    @Column(name = "NAME", nullable = false, length = 200)
    private String name;

    @Column(name = "EMAIL_ADDRESS", nullable = true, length = 200)
    private String emailAddress;

    @Column(name = "ACTIVE", nullable = false, length = 1)
    private Character active;

    @Column(name = "LAST_LOGIN_TIME", length = 50)
    private String lastLoginTime;

    @Column(name = "LAST_PASSWORD_CHANGE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastPasswordChangedDate;

    @JsonManagedReference
    @JoinColumn(name = "USER_GROUP_ID", referencedColumnName = "ID", nullable = false)
    @OneToOne
    private UserGroup userGroup;

    @Column(name = "WRONG_ATTEMPT_COUNT", nullable = true)
    private int wrongAttemptCount;

    @Column(name = "BLOCKED_DATE", nullable = true)
    @Temporal(TemporalType.TIMESTAMP)
    private Date blockedDate;

    @Column(name = "CONTACT_NO", length = 50)
    private String contactNo;

    @Column(name = "IS_MFA_ENABLE", length = 1)
    private Character isMfaEnabled;

    @Column(name = "SECRET", length = 50)
    private String secret;

    @Column(name = "IMAGE_URL", length = 500)
    private String imageUrl;

    @Column(name = "HAS_ENFORCED_MFA", length = 1)
    private Character hasEnforcedMfa;

    @JoinColumn(name = "COMPANY_ID", referencedColumnName = "ID", nullable = true)
    @ManyToOne(optional = true)
    private Company company;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "EMAIL_VERIFY")
    private Character emailVerify;

    @Column(name = "PROVIDER")
    private String provider;

    @Column(name = "PROVIDER_ID")
    private String providerId;

    @CreationTimestamp
    @Column(name = "CREATED_DATE", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @JoinColumn(name = "CREATED_BY", referencedColumnName = "ID", updatable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private ApplicationUser createdBy;

    @LastModifiedDate
    @Column(name = "LAST_MODIFIED_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;

    @JsonBackReference
    @JoinColumn(name = "LAST_MODIFIED_BY", referencedColumnName = "ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private ApplicationUser lastModifiedBy;
}
