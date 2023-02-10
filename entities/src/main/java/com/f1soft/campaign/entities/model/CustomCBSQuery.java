package com.f1soft.campaign.entities.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CacheConcurrencyStrategy;

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
@Table(name = "CUSTOM_CBS_QUERY")
public class CustomCBSQuery implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @Column(name = "QUERY_CODE")
    private String queryCode;
    @Column(name = "SQL_QUERY", columnDefinition = "TEXT")
    private String sqlQuery;
    @Column(name = "QUERY_DESCRIPTION")
    private String queryDescription;
    @JoinColumn(name = "CUSTOM_CBS_CONNECTION_ID", referencedColumnName = "ID")
    @ManyToOne()
    private CustomCBSConnection customCbsConnection;
    @Column(name = "CBS_DISTRIBUTION")
    private String cbsDstribution;
    @OneToMany(mappedBy = "customCbsQuery")
    private List<EventAttribute> eventAttribute;
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

    public CustomCBSQuery() {
    }

    public CustomCBSQuery(Long id) {
        this.id = id;
    }
}
