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
@Table(name = "EVENT_ATTRIBUTE")
public class EventAttribute {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @Column(name = "COUNT")
    private Long count;
    @Column(name = "MINIMUM_AMOUNT")
    private Double minimumAmount;
    @JoinColumn(name = "CAMPAIGN_ID", referencedColumnName = "ID")
    @OneToOne(optional = false)
    private Campaign campaign;
    @JoinColumn(name = "CUSTOM_CBS_QUERY_ID", referencedColumnName = "ID")
    @ManyToOne()
    private CustomCBSQuery customCbsQuery;
}
