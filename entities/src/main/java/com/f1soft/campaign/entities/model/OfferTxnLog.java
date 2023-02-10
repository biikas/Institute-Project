package com.f1soft.campaign.entities.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author Prajwol Hada
 */
@Getter
@Setter
@Entity
@Table(name = "OFFER_TXN_LOG")
public class OfferTxnLog implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @Column(name = "ADDED_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date addedDate;
    @Column(name = "STATUS")
    private String status;
    @Column(name = "REMARKS")
    private String remarks;
    @ManyToOne
    @JoinColumn(name = "OFFER_TRANSACTION_ID")
    private OfferTransaction offerTransaction;
}
