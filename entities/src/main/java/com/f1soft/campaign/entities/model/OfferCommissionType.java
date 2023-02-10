package com.f1soft.campaign.entities.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Shreetika Panta
 */

@Getter
@Setter
@Entity
@Table(name = "OFFER_COMMISSION_TYPE")
public class OfferCommissionType implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @Column(name = "ACTIVE", nullable = false)
    private char active;
    @JoinColumn(name = "OFFER_MODE_ID", referencedColumnName = "ID")
    @ManyToOne()
    private OfferMode offerMode;
    @JoinColumn(name = "COMMISSION_TYPE_ID", referencedColumnName = "ID")
    @ManyToOne()
    private CommissionType commissionType;
}
