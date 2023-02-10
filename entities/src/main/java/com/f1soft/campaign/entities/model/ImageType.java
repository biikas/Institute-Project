package com.f1soft.campaign.entities.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Prajwol Hada
 */
@Getter
@Setter
@Entity
@Table(name = "IMAGE_TYPE")
public class ImageType implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID", nullable = false)
    private Long id;
    @Column(name = "ACTIVE", length = 1)
    private Character active;
    @Column(name = "TYPE")
    private String type;
    @Column(name = "LOCATION")
    private String location;
    @Column(name = "IMAGE_HEIGHT")
    private int imageHeight;
    @Column(name = "IMAGE_WIDTH")
    private int imageWidth;
    @Column(name = "CHECK_DIMENSION", length = 1)
    private Character checkDimension;
}
