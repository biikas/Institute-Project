package com.f1soft.campaign.entities.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * @author Prajwol hada
 */
@Getter
@Setter
@Entity
@Table(name = "OFFER_MODE")
public class OfferMode implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @Column(name = "CODE", nullable = false)
    private String code;
    @Column(name = "NAME", nullable = false)
    private String name;
    @Column(name = "ACTIVE", nullable = false)
    private char active;
    @OneToMany(mappedBy = "offerMode")
    private List<OfferCommissionType> offerCommissionType;
    @OneToMany(mappedBy = "offerMode")
    private List<OfferPackage> offerPackages;
}
