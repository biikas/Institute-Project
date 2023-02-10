package com.f1soft.campaign.entities.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * @Author Nitesh Poudel
 */
@Getter
@Setter
@Entity
@Table(name = "CAMPAIGN_EXCEL_FILES")
public class CampaignExcelFiles {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @Column(name = "FILE_NAME")
    private String fileName;
    @Column(name = "PROCESS_STATUS")
    private Integer processStatus;
    @OneToOne()
    @JoinColumn(name = "CAMPAIGN_ID", referencedColumnName = "ID")
    private Campaign campaign;
}
