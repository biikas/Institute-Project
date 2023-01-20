package com.nikosera.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity(name = "COMPANY")
public class Company extends Auditable<Company> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "CODE")
    private String code;

    @Column(name = "CONTACT_NUMBER")
    private String contactNumber;

    @Column(name = "CONTACT_ADDRESS")
    private String contactAddress;

    @Column(name = "ACTIVE", nullable = false, length = 1)
    private Character active;

    @Column(name = "URL")
    private String url;

    @Column(name = "LOGO_URL")
    private String logoUrl;

}
