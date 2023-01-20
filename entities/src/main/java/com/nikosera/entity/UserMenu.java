package com.nikosera.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Narayan Joshi
 * <narenzoshi@gmail.com>
 */

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "USER_MENU")
public class UserMenu implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;
    @Column(name = "NAME", length = 100)
    private String name;
    @Column(name = "ACTION", length = 30)
    private String action;
    @Column(name = "ACTIVE", length = 1)
    private Character active;
    @Column(name = "SORT_ORDER")
    private Integer sortOrder;
    @Column(name = "PARENT_ID")
    private Long parentId;
    @Column(name = "NAV_TYPE", length = 100)
    private String navType;
    @Column(name = "PATH")
    private String path;
    @Column(name = "ICON", length = 50)
    private String icon;
    @Column(name = "ICON_ACTIVE", length = 50)
    private String iconActive;
    @Column(name = "DESCRIPTION", length = 100)
    private String description;
    @Column(name = "IS_MULTILEVEL", length = 1, nullable = false)
    private Character isMultilevel;
    @JoinColumn(name = "PARENT_ID", referencedColumnName = "ID", insertable = false, updatable = false)
    @ManyToOne()
    private UserMenu parentMenu;

    public UserMenu(Long id) {
        this.id = id;
    }
}