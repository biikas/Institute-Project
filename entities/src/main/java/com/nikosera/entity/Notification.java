package com.nikosera.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author Narayan Joshi
 * <narenzoshi@gmail.com>
 */
@Getter
@Setter
@NoArgsConstructor
@Entity(name = "NOTIFICATION")
public class Notification implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false, precision = 22)
    private Long id;

    @Column(name = "TYPE", nullable = false, length = 200)
    private String type;

    @Column(name = "STATUS", nullable = false, length = 200)
    private String status;

    @Column(name = "RECORDED_DATA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date recordedDate;

    @Column(name = "NOTIFIED_TO", nullable = false)
    private String notifiedTo;

    @Column(name = "SUBJECT", length = 255)
    private String subject;

    @Column(name = "SENT_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date sentDate;

    @Column(name = "TEMPLATE_NAME", length = 255)
    private String templateName;

    @Column(name = "IS_PUBLISHED", length = 255)
    private Character isPublished;

    @OneToMany(mappedBy = "notification", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<NotificationParameter> notificationParametersList;
}