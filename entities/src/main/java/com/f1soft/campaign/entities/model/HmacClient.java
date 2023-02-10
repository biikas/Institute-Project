package com.f1soft.campaign.entities.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "HMAC_CLIENT")
public class HmacClient implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID", nullable = false, precision = 22)
    private Long id;

    @Basic(optional = false)
    @Column(name = "API_SECRET", nullable = false, length = 255)
    private String apiSecret;

    @Basic(optional = false)
    @Column(name = "API_KEY", nullable = false, length = 50)
    private String apiKey;

    @Basic(optional = false)
    @Column(name = "ACTIVE", nullable = false, length = 1)
    private Character active;

    @Basic(optional = false)
    @Column(name = "ADDED_DATE", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date addedDate;

    @Basic(optional = false)
    @Column(name = "CLIENT_KEY", nullable = false, length = 50)
    private String clientKey;
}
