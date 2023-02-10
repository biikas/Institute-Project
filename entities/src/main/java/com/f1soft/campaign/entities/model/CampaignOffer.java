package com.f1soft.campaign.entities.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Prajwol hada
 */
@Getter
@Setter
@Entity
@Table(name = "CAMPAIGN_OFFER")
public class CampaignOffer implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @Column(name = "ACTIVE", nullable = false)
    private char active;
    @Column(name = "MIN_AMOUNT")
    private Double minAmount;
    @Column(name = "MAX_AMOUNT")
    private Double maxAmount;
    @Column(name = "VALUE")
    private String value;
    @Column(name = "COMMISSION_TYPE")
    private String commissionType;
    @JoinColumn(name = "CAMPAIGN_ID", referencedColumnName = "ID")
    @ManyToOne()
    private Campaign campaign;
    @JoinColumn(name = "OFFER_MODE_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private OfferMode offerMode;
}
