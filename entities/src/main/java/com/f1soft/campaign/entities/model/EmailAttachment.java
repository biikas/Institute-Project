package com.f1soft.campaign.entities.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "EMAIL_ATTACHMENT")
public class EmailAttachment implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID", nullable = false, precision = 38, scale = 0)
    private Long id;
    @JoinColumn(name = "EMAIL_TO_SEND_ID", referencedColumnName = "ID", nullable = true)
    @ManyToOne(optional = true)
    private EmailToSend emailToSend;
    @Lob
    @Column(name = "ATTACHED_FILE")
    private byte[] attachedFile;
    @Column(name = "GENERATED_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date generatedDate;
    @Column(name = "ATTACHED_FILE_PATH")
    private String attachedFilePath;

    public EmailAttachment() {
    }

    public EmailAttachment(Long id) {
        this.id = id;
    }
}