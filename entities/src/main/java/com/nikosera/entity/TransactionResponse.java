package com.nikosera.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author narayanjoshi
 * @email <narayan.joshi>
 * Created Date: 12/24/22
 */


@Getter
@Setter
@Entity(name = "TRANSACTION_RESPONSE")
public class TransactionResponse implements Serializable {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "RECORDED_DATE")
    private Date recordedDate;

    @JoinColumn(name = "TRANSACTION_REQUEST_ID", referencedColumnName = "ID", nullable = false)
    @ManyToOne(fetch = FetchType.EAGER)
    private TransactionRequest transactionRequest;

    @JoinColumn(name = "STATUS_ID", referencedColumnName = "ID")
    @ManyToOne()
    private Status status;

    @Column(name = "PIDX")
    private String pidx;

    @JoinColumn(name = "VENDOR_ID", referencedColumnName = "ID", insertable = false, updatable = false)
    @ManyToOne()
    private Vendor vendor;

}
