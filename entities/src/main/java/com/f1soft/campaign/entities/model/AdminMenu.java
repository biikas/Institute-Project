package com.f1soft.campaign.entities.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "ADMIN_MENU")
public class AdminMenu implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID", nullable = false)
    private Long id;
    @Column(name = "ACTIVE", length = 1)
    private Character active;
    @Column(name = "NAME", length = 100)
    private String name;
    @Column(name = "DESCRIPTION")
    private String description;
    @Column(name = "CODE", length = 50)
    private String code;
    @JoinColumn(name = "ADMIN_MENU_GROUP_ID", referencedColumnName = "ID")
    @ManyToOne()
    private AdminMenuGroup adminMenuGroup;

}
