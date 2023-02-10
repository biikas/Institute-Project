package com.f1soft.campaign.entities.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * @author Shreetika Panta
 */

@Getter
@Setter
@Entity
@Table(name = "DATA_PACKAGE_CAMPAIGN_USER")
public class DataPackageCampaignUser {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "PACKAGE_ITEM_ID")
    private PackageItem packageItem;
    @JoinColumn(name = "OFFER_TRANSACTION_ID")
    @OneToOne(fetch = FetchType.LAZY)
    private OfferTransaction offerTransaction;
    @JoinColumn(name = "CAMPAIGN_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Campaign campaign;
    @Column(name = "USERNAME")
    private String username;

}
