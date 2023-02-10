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
@Table(name = "CAMPAIGN_ALLOWED_USER_TYPE")
public class CampaignAllowedUserType implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @Column(name = "ALL_USER", nullable = false)
    private String allUser;
    @Column(name = "HAS_EXCLUDED_USER", nullable = false)
    private String hasExcludedUser;
    @JoinColumn(name = "CAMPAIGN_ID", referencedColumnName = "ID")
    @OneToOne()
    private Campaign campaign;
}
