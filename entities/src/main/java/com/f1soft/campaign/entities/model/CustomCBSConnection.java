package com.f1soft.campaign.entities.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author Shreetika Panta
 */
@Getter
@Setter
@Entity
@Table(name = "CUSTOM_CBS_CONNECTION")
public class CustomCBSConnection implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @Column(name = "CBS_CONNECTION_URL")
    private String cbsConnectionURL;
    @Column(name = "CBS_DRIVER_NAME")
    private String cbsDriverName;
    @Column(name = "CBS_USERNAME")
    private String cbsUsername;
    @Column(name = "CBS_PASSWORD")
    private String cbsPassword;
    @OneToMany(mappedBy = "customCbsConnection")
    private List<CustomCBSQuery> customCbsQuery;
    @Column(name = "ACTIVE", length = 1)
    private Character active;
    @Basic(optional = false)
    @Column(name = "CREATED_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    @JoinColumn(name = "CREATED_BY", referencedColumnName = "ID", nullable = true)
    @ManyToOne
    private ApplicationUser createdBy;
    @Column(name = "MODIFIED_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifiedDate;
    @JoinColumn(name = "MODIFIED_BY", referencedColumnName = "ID", nullable = true)
    @ManyToOne
    private ApplicationUser modifiedBy;

    public CustomCBSConnection() {
    }

    public CustomCBSConnection(Long id) {
        this.id = id;
    }
}
