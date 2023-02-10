package com.f1soft.campaign.entities.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author yogesh
 */
@Getter
@Setter
@Entity
@Table(name = "REQUEST_INFO")
public class RequestInfo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @JoinColumn(name = "APPLICATION_USER_ID", nullable = true)
    @ManyToOne()
    private ApplicationUser applicationUser;
    @JoinColumn(name = "CAMPAING_ID")
    @ManyToOne()
    private Campaign campaign;
    @Column(name = "REQUESTED_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date requestedDate;
    @OneToOne(mappedBy = "requestInfo")
    private IsoTxnRequest isoTxnRequest;
    @JoinColumn(name = "OFFER_TRANSACTION_ID", referencedColumnName = "ID")
    @ManyToOne()
    private OfferTransaction offerTransaction;

    public RequestInfo() {
    }

    public RequestInfo(Long id) {
        this.id = id;
    }

}
