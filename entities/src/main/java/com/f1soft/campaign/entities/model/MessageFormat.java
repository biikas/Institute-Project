package com.f1soft.campaign.entities.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author yogesh
 */
@Getter
@Setter
@Entity
@Table(name = "MESSAGE_FORMAT")
public class MessageFormat implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID", nullable = false, precision = 19, scale = 0)
    private Long id;
    @Basic(optional = false)
    @Column(name = "MESSAGE", nullable = false, length = 250)
    private String message;
    @Basic(optional = false)
    @Column(name = "MESSAGE_CODE", nullable = false, length = 255)
    private String messageCode;
    @Column(name = "DESCRIPTION", length = 200)
    private String description;
    @Column(name = "PROVIDED_MODIFY_LENGTH")
    private Integer modifyLength;
    @Transient
    private Long messageTypeID;

    public MessageFormat() {
    }

    public MessageFormat(Long id) {
        this.id = id;
    }
}