package com.nikosera.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author narayanjoshi
 * @email <narayan.joshi>
 * Created Date: 12/12/22
 */

@Getter
@Setter
@Entity(name = "TRANSACTION_REQUEST")
public class TransactionRequest implements Serializable {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "RECORDED_DATE")
    private Date recordedDate;

    @JoinColumn(name = "VENDOR_ID", referencedColumnName = "ID", insertable = false, updatable = false)
    @ManyToOne()
    private Vendor vendor;

    @JoinColumn(name = "STATUS_ID", referencedColumnName = "ID")
    @ManyToOne()
    private Status status;

    @Column(name = "AMOUNT")
    private Double amount;

    @Column(name = "ORDER_ID")
    private String orderId;

    @Column(name = "ORDER_NAME")
    private String orderName;

    @Column(name = "INTIATOR_NAME")
    private String initiatorName;

    @Column(name = "INITIATOR_EMAIL")
    private String initiatorEmail;

    @Column(name = "INITIATOR_MOBILE")
    private String initiatorMobileNumber;
}
