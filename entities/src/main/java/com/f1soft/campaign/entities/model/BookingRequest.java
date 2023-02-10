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
@Table(name = "BOOKING_REQUEST")
public class BookingRequest implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @Column(name = "MOBILE_NUMBER", nullable = false)
    private String mobileNumber;
    @Column(name = "RECORDED_DATE", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date recordedDate;
    @Column(name = "EXPIRY_DATE", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date expiryDate;
    @Column(name = "SERVICE_CODE", nullable = false)
    private String serviceCode;
    @Column(name = "STATUS", nullable = false)
    private String status;
    @Column(name = "BOOKING_ID", nullable = false)
    private String bookingId;
    @JoinColumn(name = "CAMPAIGN_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Campaign campaign;
    @JoinColumn(name = "CHANNEL_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Channel channel;
    @Column(name = "PROFILE_ID", nullable = false)
    private Long profileId;
    @Column(name = "TXN_AMOUNT", nullable = false)
    private Double txnAmount;
    @Column(name = "EMAIL_ADDRESS")
    private String emailAddress;
    @Column(name = "CUSTOMER_NAME")
    private String customerName;
}
