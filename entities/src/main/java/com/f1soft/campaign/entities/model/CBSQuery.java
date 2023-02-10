package com.f1soft.campaign.entities.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Kailash
 */
@Getter
@Setter
@Entity
@Table(name = "CBS_QUERY")
public class CBSQuery implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "QUERY_CODE")
    private String queryCode;
    @Column(name = "SQL_QUERY", columnDefinition = "TEXT")
    private String sqlQuery;
    @Column(name = "QUERY_DESCRIPTION")
    private String queryDescription;
    @JoinColumn(name = "CBS_CONNECTION_ID", referencedColumnName = "ID")
    @ManyToOne()
    private CBSConnection cbsConnection;
    @Column(name = "CBS_DISTRIBUTION")
    private String cbsDstribution;

    public CBSQuery() {
    }

    public CBSQuery(Integer id) {
        this.id = id;
    }
}
