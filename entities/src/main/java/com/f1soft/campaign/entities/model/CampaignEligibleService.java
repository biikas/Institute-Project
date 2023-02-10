package com.f1soft.campaign.entities.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Prajwol hada
 */
@Getter
@Setter
@Entity
@Table(name = "CAMPAIGN_ELIGIBLE_SERVICE")
public class CampaignEligibleService implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @Column(name = "ACTIVE", nullable = false)
    private char active;
    @JoinColumn(name = "CAMPAIGN_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Campaign campaign;
    @Column(name = "SERVICE_CODE", nullable = false)
    private String serviceCode;
    @Column(name = "SERVICE_NAME", nullable = false)
    private String serviceName;
    @Column(name = "MODULE", nullable = false)
    private String module;
    @Column(name = "MODE", nullable = false)
    private String mode;
}
