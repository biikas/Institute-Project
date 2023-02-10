package com.f1soft.campaign.entities.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author yogesh
 */
@Getter
@Setter
@Entity
@Table(name = "ISO_TXN_REQUEST", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"REQUEST_INFO_ID"})})
public class IsoTxnRequest implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @Basic(optional = false)
    @Column(name = "FROM_ACCOUNT")
    private String fromAccount;
    @Basic(optional = false)
    @Column(name = "TO_ACCOUNT")
    private String toAccount;
    @Column(name = "TXN_STATUS")
    private Integer txnStatus;
    @Column(name = "TXN_ERROR_CODE")
    private String txnErrorCode;
    @Column(name = "TXN_ERROR_DESC")
    private String txnErrorDesc;
    @Column(name = "TXN_TRACE_ID", length = 50)
    private String txnTraceId;
    @Column(name = "AMOUNT", precision = 18, scale = 2)
    private BigDecimal amount;
    @Column(name = "TXN_AMOUNT", precision = 18, scale = 2)
    private BigDecimal txnAmount;
    @Column(name = "TXN_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date txnDate;
    @JoinColumn(name = "REQUEST_INFO_ID")
    @OneToOne(fetch = FetchType.LAZY)
    private RequestInfo requestInfo;
    @Column(name = "ISO_RESPONSE_ID")
    private String isoResponseId;
    @Column(name = "COMMISSION_INVOLVED", nullable = false)
    private Character commissionInvolved;
    @Column(name = "COMMISSION_PAID_BY")
    private String commissionPaidBy;
    @Column(name = "COMMISSION_AMOUNT", precision = 18, scale = 2)
    private BigDecimal commissionAmount;
    @Column(name = "UTILITY_PAYMENT_STATUS")
    private Integer utilityPaymentStatus;
    @Column(name = "UTILITY_ERROR_CODE", length = 10)
    private String utilityErrorCode;
    @Column(name = "UTILITY_ERROR_DESCRIPTION", length = 80)
    private String utilityErrorDesc;
    @Column(name = "UTILITY_TRACE_ID", length = 50)
    private String utilityTraceId;
    @Column(name = "REVERSED")
    private Character reversed;
    @Column(name = "EXTRA_CHARGE_TYPE", length = 10)
    private String extraChargeType;
    @Column(name = "EXTRA_CHARGE", precision = 18, scale = 2)
    private BigDecimal extraCharge;

    public IsoTxnRequest() {
    }

    public IsoTxnRequest(Long id) {
        this.id = id;
    }

    public IsoTxnRequest(Long id, String fromAccount, String toAccount) {
        this.id = id;
        this.fromAccount = fromAccount;
        this.toAccount = toAccount;
    }
}