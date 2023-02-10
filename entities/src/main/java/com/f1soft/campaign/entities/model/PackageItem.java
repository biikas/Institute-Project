package com.f1soft.campaign.entities.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * @Author Nitesh Poudel
 */
@Getter
@Setter
@Entity
@Table(name = "PACKAGE_ITEM")
public class PackageItem implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @Basic(optional = false)
    @Column(name = "CODE")
    private String code;
    @Basic(optional = false)
    @Column(name = "NAME")
    private String name;
    @Basic(optional = false)
    @Column(name = "REGEX")
    private String regex;
    @Basic(optional = false)
    @Column(name = "ACTIVE")
    private char active;
    @JoinColumn(name = "PACKAGE_ID", referencedColumnName = "ID")
    @ManyToOne()
    private OfferPackage offerPackage;
    @Column(name = "PRODUCT_CODE")
    private String productCode;
    @Column(name = "AMOUNT")
    private Double amount;

}
