package com.f1soft.campaign.entities.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "ADMIN_MENU_GROUP")
public class AdminMenuGroup implements Serializable {

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
    @Column(name = "CODE", length = 50)
    private String code;
    @OneToMany(mappedBy = "adminMenuGroup", fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    private List<AdminMenu> adminMenus;

}
