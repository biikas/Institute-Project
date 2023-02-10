package com.f1soft.campaign.entities.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "ADMIN_ROLES_PROFILE")
public class AdminRolesProfile implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID", nullable = false, precision = 38, scale = 0)
    private Long id;
    @JoinColumn(name = "ADMIN_MENU_ID", referencedColumnName = "ID", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private AdminMenu adminMenu;
    @Column(name = "ENABLED", length = 1)
    private char enabled;
    @JoinColumn(name = "ADMIN_TYPE_ID", referencedColumnName = "ID", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private AdminType adminType;
}
