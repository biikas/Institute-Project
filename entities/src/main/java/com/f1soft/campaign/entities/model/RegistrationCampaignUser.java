package com.f1soft.campaign.entities.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

/**
 * @author Shreetika Panta
 */

@Getter
@Setter
@Entity
@Table(name = "REGISTRATION_CAMPAIGN_USER")
public class RegistrationCampaignUser {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @Column(name = "USERNAME")
    private String username;
    @Column(name = "REGISTRATION_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date registrationDate;
    @Column(name = "ACCOUNT_NUMBER")
    private String accountNumber;
    @Column(name = "RECORDED_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date recordedDate;
    @Column(name = "PROFILE_ID")
    private Long profileId;
    @Column(name = "ACCOUNT_TYPE")
    private String accountType;
    @Column(name = "CAMPAIGN_ID")
    private Long campaignId;
}
