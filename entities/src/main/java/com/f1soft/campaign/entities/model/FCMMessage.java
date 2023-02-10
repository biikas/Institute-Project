package com.f1soft.campaign.entities.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author Rashim Dhaubanjar
 */
@Getter
@Setter
@Entity
@Table(name = "FCM_MESSAGE")
public class FCMMessage implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @Column(name = "TITLE")
    private String title;
    @Column(name = "MESSAGE", columnDefinition = "TEXT")
    private String message;
    @Column(name = "MESSAGE_DETAIL", columnDefinition = "TEXT")
    private String messageDetail;
    @Column(name = "BANNER_URL")
    private String bannerUrl;
    @Column(name = "BANNER_IMAGE")
    private String bannerImage;
    @Column(name = "SENT_BY")
    private Long sentBy;
    @Column(name = "SEND_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date sentDate;
    @Column(name = "STATUS")
    private String status;
    @Column(name = "MESSAGE_ID")
    private String messageId;
    @Column(name = "REMARKS")
    private String remarks;
    @Column(name = "TOPIC", nullable = false)
    private String topic;

    public FCMMessage() {
    }
}
