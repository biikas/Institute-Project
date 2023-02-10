package com.f1soft.campaign.entities.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @Author Nitesh Poudel
 */
@Setter
@Getter
@Entity
@Table(name = "CAMPAIGN_TOTAL_REDEEM")
public class CampaignTotalRedeem implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @Column(name = "REDEEM_AMOUNT")
    private Double redeemAmount;
    @Column(name = "OFFER_AMOUNT")
    private Double offerAmount;
    @Column(name = "REDEEM_COUNT")
    private Integer redeemCount;
    @Column(name = "RECORDED_DATE")
    private Date recordedDate;
    @Column(name = "LAST_UPDATED_DATE")
    private Date lastUpdatedDate;
    @OneToOne
    @JoinColumn(name = "CAMPAIGN_ID", referencedColumnName = "ID")
    private Campaign campaign;
}
