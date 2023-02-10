package com.f1soft.campaign.entities.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * @author Bikash Shah
 */
@Getter
@Setter
@Entity
@Table(name="VENDOR_RESPONSE")
public class VendorResponse {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @Column(name = "RESPONSE_STATUS")
    private Integer responseStatus;
    @Column(name = "RESPONSE_CODE", length = 10)
    private String responseCode;
    @Column(name = "RESPONSE_DESCRIPTION", length = 250)
    private String responseDesc;
    @Column(name = "TRACE_ID", length = 50)
    private String traceId;
    @JoinColumn(name = "REQUEST_INFO_ID", referencedColumnName = "ID")
    @OneToOne(fetch = FetchType.LAZY)
    private RequestInfo requestInfo;

}
