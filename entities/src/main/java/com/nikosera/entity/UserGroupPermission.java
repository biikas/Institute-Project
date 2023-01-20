package com.nikosera.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * @author Narayan Joshi
 * <narenzoshi@gmail.com>
 */
@Getter
@Setter
@NoArgsConstructor
@Entity(name = "USER_GROUP_PERMISSION")
public class UserGroupPermission extends Auditable<ApplicationUser> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false, precision = 38)
    private Long id;
    @JoinColumn(name = "USER_GROUP_ID", referencedColumnName = "ID", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private UserGroup userGroup;
    @JoinColumn(name = "USER_MENU_ID", referencedColumnName = "ID", nullable = false)
    @ManyToOne(fetch = FetchType.EAGER)
    private UserMenu userMenu;
    @Column(name = "ENABLE", length = 1)
    private char enabled;

}
