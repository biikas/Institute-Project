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
@Table(name = "CAMPAIGN_ELIGIBLE_USER")
public class CampaignEligibleUser implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @Column(name = "USERNAME")
    private String userName;
    @Column(name = "BULK_FILE_NAME")
    private String bulkFileName;
    @JoinColumn(name = "CAMPAIGN_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Campaign campaign;
    @Column(name = "PROMO_CODE")
    private String promoCode;

}
