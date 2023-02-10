package com.f1soft.campaign.entities.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "EMAIL_PARAMETER")
public class EmailParameter implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID", nullable = false, precision = 38, scale = 0)
    private Long id;
    @Basic(optional = false)
    @Column(name = "PARAM_KEY", nullable = false, length = 50)
    private String paramKey;
    @Basic(optional = false)
    @Column(name = "PARAM_VALUE", nullable = false, length = 250)
    private String paramValue;
    @JoinColumn(name = "EMAIL_TO_SEND_ID", referencedColumnName = "ID", nullable = true)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private EmailToSend emailToSend;

    public EmailParameter() {
    }

    public EmailParameter(Long id) {
        this.id = id;
    }
}