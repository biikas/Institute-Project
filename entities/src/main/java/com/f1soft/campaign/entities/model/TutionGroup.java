package com.f1soft.campaign.entities.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

/**
 * @author Bikash Shah
 */
@Getter
@Setter
@Entity
@Table(name = "TUTION_GROUP")
public class TutionGroup {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @Column(name = "NAME", nullable = false)
    private String name;
    @Column(name = "Description")
    private String description;
    @Column(name = "Active")
    private Character active;
    @Column(name = "assignedTeacherId")
    private Long assignedTeacherId;
    @Column(name = "CREATED_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    @JoinColumn(name = "CREATED_BY", referencedColumnName = "ID", nullable = true)
    @ManyToOne()
    private ApplicationUser createdBy;
}
