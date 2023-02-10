package com.f1soft.campaign.entities.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @Author Nitesh Poudel
 */
@Getter
@Setter
@Entity
@Table(name = "GIFT_CARD_ELIGIBLE_USER")
public class GiftCardEligibleUser implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
    @Column(name = "USERNAME")
    private String username;
    @Column(name = "REDEEMED")
    private Character redeemed;
    @ManyToOne()
    @JoinColumn(name = "OFFER_TRANSACTION_ID", referencedColumnName = "ID")
    private OfferTransaction offerTransaction;
    @ManyToOne()
    @JoinColumn(name = "CAMPAIGN_ID", referencedColumnName = "ID")
    private Campaign campaign;
    @ManyToOne()
    @JoinColumn(name = "GIFT_CARD_ID", referencedColumnName = "ID")
    private GiftCard giftCard;

}
