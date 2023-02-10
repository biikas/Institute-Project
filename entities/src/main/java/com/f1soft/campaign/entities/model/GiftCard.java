package com.f1soft.campaign.entities.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author <krishna.pandey@f1soft.com>
 */
@Getter
@Setter
@Entity
@Table(name = "GIFT_CARD")
public class GiftCard implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;
    @JoinColumn(name = "GIFT_CARD_PROVIDER_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private GiftCardProvider giftCardProvider;
    @Column(name = "ACTIVE", length = 1)
    private Character active;
    @Column(name = "CODE", length = 50)
    private String code;
    @Column(name = "NAME")
    private String name;
    @Column(name = "EXPIRY_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date expiryDate;
    @Column(name = "AMOUNT")
    private Double amount;
    @JoinColumn(name = "CREATED_BY", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private ApplicationUser createdBy;
    @Column(name = "CREATED_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    @JoinColumn(name = "MODIFIED_BY", referencedColumnName = "ID")
    @ManyToOne()
    private ApplicationUser modifiedBy;
    @Column(name = "MODIFIED_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifiedDate;
}
