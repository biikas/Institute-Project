package com.nikosera.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Narayan Joshi
 * <narenzoshi@gmail.com>
 */
@Getter
@Setter
@NoArgsConstructor
@Entity(name = "NOTIFICATION_PARAMETER")
public class NotificationParameter implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID", nullable = false, precision = 22)
    private Long id;
    @Basic(optional = false)
    @Column(name = "PARAM_KEY", nullable = false, length = 200)
    private String paramKey;
    @Basic(optional = false)
    @Column(name = "PARAM_VALUE", nullable = false, length = 200)
    private String paramValue;
    @JoinColumn(name = "NOTIFICATION_ID", referencedColumnName = "ID", nullable = true)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Notification notification;
}