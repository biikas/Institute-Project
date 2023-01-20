package com.nikosera.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;

/**
 * @author Narayan Joshi
 * <narenzoshi@gmail.com>
 */
@Getter
@Setter
@NoArgsConstructor
@Entity(name = "USER_GROUP")
public class UserGroup extends Auditable<ApplicationUser> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false, precision = 38)
    private Long id;
    @Column(name = "NAME", nullable = false, length = 80)
    private String name;
    @Column(name = "DESCRIPTION")
    private String description;
    @Column(name = "ACTIVE", nullable = false)
    private Character active;
    @JsonIgnore
    @OneToMany(mappedBy = "userGroup", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @Fetch(FetchMode.SUBSELECT)
    private List<UserGroupPermission> permissions;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "PARENT_ID", referencedColumnName = "ID")
    private UserGroup parent;

    public UserGroup(Long id) {
        this.id = id;
    }
}
