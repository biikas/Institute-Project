package com.f1soft.campaign.entities.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author yogesh
 */
@Getter
@Setter
@Entity
@Table(name = "EMAIL_TO_SEND")
public class EmailToSend implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID", nullable = false, precision = 38, scale = 0)
    private Long id;
    @Basic(optional = false)
    @Column(name = "TEMPLATE_NAME", nullable = false, length = 50)
    private String templateName;
    @Basic(optional = false)
    @Column(name = "SUBJECT", nullable = false, length = 250)
    private String subject;
    @Basic(optional = false)
    @Column(name = "IS_SENT", nullable = false, length = 1)
    private Character isSent;
    @Basic(optional = false)
    @Column(name = "RECORDED_DATE", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date recordedDate;
    @Column(name = "SENT_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date sentDate;
    @Basic(optional = false)
    @Column(name = "RECEIVER_ADDRESS", nullable = false, length = 250)
    private String receiverAddress;
    @OneToMany(mappedBy = "emailToSend", fetch = FetchType.EAGER)
    private List<EmailAttachment> emailAttachmentList;
    @OneToMany(mappedBy = "emailToSend", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<EmailParameter> emailParametersList;
    @OneToMany(mappedBy = "emailToSend", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<EmailDynamicParameter> emailDynamicParameterList;

    public EmailToSend() {
    }

    public EmailToSend(Long id) {
        this.id = id;
    }

}