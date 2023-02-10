package com.f1soft.campaign.entities.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * @author bijay.shrestha
 */
@Getter
@Setter
@Entity
@Table(name = "CBS_CONNECTION")
public class CBSConnection implements Serializable {

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
    @OneToMany(mappedBy = "cbsConnection")
    private List<CBSQuery> cbsQuery;

    public CBSConnection() {
    }

    public CBSConnection(Long id) {
        this.id = id;
    }
}
