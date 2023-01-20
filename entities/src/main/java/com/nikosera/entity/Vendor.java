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
@Entity(name = "VENDOR")
public class Vendor implements Serializable {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ADDED_DATE")
    private Date addedDate;

    @Column(name = "CODE")
    private String code;

    @Column(name = "NAME")
    private String name;

    @Column(name = "ACTIVE", nullable = false, length = 1)
    private Character active;
}
