package com.f1soft.campaign.entities.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author Prajwol hada
 */
@Getter
@Setter
@Entity
@Table(name = "CAMPAIGN")
public class Campaign implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @Column(name = "TITLE", nullable = false)
    private String title;
    @Column(name = "DESCRIPTION", nullable = false)
    private String description;
    @Column(name = "ACTIVE", nullable = false)
    private char active;
    @JoinColumn(name = "CREATED_BY", referencedColumnName = "ID", nullable = true)
    @ManyToOne(optional = false)
    private ApplicationUser createdBy;
    @Column(name = "CREATED_DATE", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    @JoinColumn(name = "MODIFIED_BY", referencedColumnName = "ID")
    @ManyToOne()
    private ApplicationUser modifiedBy;
    @Column(name = "MODIFIED_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifiedDate;
    @Column(name = "PROMO_CODE", nullable = true)
    private String promoCode;
    @Column(name = "START_DATE", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date startDate;
    @Column(name = "END_DATE", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date endDate;
    @Column(name = "TOTAL_ISSUED", nullable = false)
    private Integer totalIssued;
    @Column(name = "USAGE_PER_CUSTOMER", nullable = false)
    private Integer usagePerCostumer;
    @Column(name = "BOOKING_EXPIRY_TIME", nullable = false)
    private Integer bookingExpiryTime;
    @Column(name = "CAMPAIGN_MODE", nullable = false)
    private String campaignMode;
    @Column(name = "EVENT_TYPE", nullable = false)
    private String eventType;
    @Column(name = "POLICY", nullable = false)
    private String policy;
    @Column(name = "OFFER_LINK")
    private String offerLink;
    @Column(name = "STATUS")
    private String status;
    @Column(name = "IMAGE")
    private String image;
    @Column(name = "ALLOWED_USERS", nullable = false)
    private String allowedUsers;
    @Column(name = "SHORT_DESCRIPTION", nullable = false)
    private String shortDescription;
    @OneToMany(mappedBy = "campaign", fetch = FetchType.EAGER)
    private List<CampaignOffer> campaignOffer;
    @OneToMany(mappedBy = "campaign", fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    private List<CampaignEligibleService> campaignEligibleServices;
    @OneToMany(mappedBy = "campaign", fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    private List<CampaignAllowedChannel> campaignAllowedChannels;
    @Column(name = "REMARKS")
    private String remarks;
    @Column(name = "OFFER_ACCOUNT")
    private String offerAccount;
    @JoinColumn(name = "EVENT_TYPE_ID")
    @OneToOne(fetch = FetchType.LAZY)
    private EventType eventTypeId;
    @OneToOne(mappedBy = "campaign")
    private EventAttribute eventAttribute;
    @Column(name = "PROMO_CODE_MODE", nullable = false)
    private String promoCodeMode;
    @OneToOne(mappedBy = "campaign")
    private CampaignTotalRedeem campaignTotalRedeem;


}
