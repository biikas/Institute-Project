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
@Table(name = "OFFER_TRANSACTION")
public class OfferTransaction implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @Column(name = "TRANSACTION_ID", nullable = true)
    private Long transactionId;
    @Column(name = "MOBILE_NUMBER", nullable = false)
    private String mobileNumber;
    @Column(name = "RECORDED_DATE", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date recordedDate;
    @Column(name = "TRANSACTION_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date transactionDate;
    @Column(name = "CLAIM_DATE", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date claimDate;
    @Column(name = "PROMO_CODE", nullable = false)
    private String promoCode;
    @Column(name = "AMOUNT", nullable = false)
    private Double amount;
    @Column(name = "STATUS", nullable = false)
    private String status;
    @Column(name = "TRANSACTION_AMOUNT", nullable = false)
    private Double transactionAmount;
    @Column(name = "SERVICE_CODE", nullable = true)
    private String serviceCode;
    @Column(name = "SERVICE_NAME", nullable = true)
    private String serviceName;
    @Column(name = "BOOKING_ID", nullable = true)
    private String bookingId;
    @Column(name = "ACCOUNT_NUMBER", nullable = false)
    private String accountNumber;
    @Column(name = "REMARKS")
    private String remarks;
    @Column(name = "PROFILE_ID", nullable = true)
    private Long profileId;
    @JoinColumn(name = "CAMPAIGN_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Campaign campaign;
    @JoinColumn(name = "CAMPAIGN_OFFER_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private CampaignOffer campaignOffer;
    @JoinColumn(name = "CHANNEL_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Channel channel;
    @Column(name = "TXN_STATUS")
    private String transactionStatus;
    @OneToOne(mappedBy = "offerTransaction")
    private DataPackageCampaignUser dataPackageCampaignUser;
    @Column(name = "CUSTOMER_NAME")
    private String customerName;

    public OfferTransaction() {
    }

}
