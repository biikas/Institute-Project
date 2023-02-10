package com.f1soft.campaign.entities.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.security.Principal;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "CLIENT")
public class Client implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID", nullable = false)
    private Long id;
    @Basic(optional = false)
    @Column(name = "USERNAME", nullable = false, length = 50)
    private String username;
    @Column(name = "NAME")
    private String name;
    @Column(name = "DESCRIPTION")
    private String description;
    @Column(name = "ACTIVE", nullable = false)
    private char active;
    @Column(name = "API_SECRET")
    private String apiSecret;
    @Basic(optional = false)
    @JoinColumn(name = "CREATED_BY", referencedColumnName = "ID")
    @ManyToOne
    private ApplicationUser createdBy;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATED_DATE", updatable = false)
    private Date createdDate;
}
