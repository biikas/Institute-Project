package com.f1soft.campaign.entities.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

/**
 * @author Shreetika Panta
 */

@Getter
@Setter
@Entity
@Table(name = "TRANSACTION_CAMPAIGN_USER")
public class TransactionCampaignUser {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @Column(name = "USERNAME")
    private String username;
    @JoinColumn(name = "CAMPAIGN_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Campaign campaign;
    @Column(name = "RECORDED_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date recordedDate;
}
