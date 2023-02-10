package com.f1soft.campaign.entities.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Prajwol hada
 */
@Getter
@Setter
@Entity
@Table(name = "BOOKING_LOG")
public class BookingLog implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @Column(name = "TOTAL_BOOKED", nullable = false)
    private Integer totalBooked;
    @Column(name = "TOTAL_USED", nullable = false)
    private Integer totalUsed;
    @JoinColumn(name = "CAMPAIGN_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Campaign campaign;

}
