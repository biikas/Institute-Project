package com.f1soft.campaign.entities.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * @author Bikash Shah
 */
@Getter
@Setter
@Entity
@Table(name = "OFFER_PRODUCT")
public class OfferProduct {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;

    @JoinColumn(name = "OFFER_MODE_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private OfferMode offerMode;

    @Column(name = "PRODUCT_CODE")
    private String productCode;

    @Column(name="REGEX")
    private String regex;

    @Column(name="ACTIVE")
    private Character active;

}
