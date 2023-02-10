package com.f1soft.campaign.entities.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author <krishna.pandey@f1soft.com>
 */
@Getter
@Setter
@Entity
@Table(name = "GIFT_CARD_PROVIDER")
public class GiftCardProvider implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;
    @Column(name = "ACTIVE", length = 1)
    private Character active;
    @Column(name = "CODE", length = 50)
    private String code;
    @Column(name = "NAME")
    private String name;
}
