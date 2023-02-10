package com.f1soft.campaign.entities.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "ADMIN_PASSWORD_CHANGE_LOG")
public class AdminPasswordChangeLog {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;

    @JoinColumn(name = "APPLICATION_USER_ID", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false)
    private ApplicationUser userId;

    @Column(name = "RECORDED_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date recordedDate;

    @Column(name = "PASSWORD", length = 200)
    private String password;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ApplicationUser getUserId() {
        return userId;
    }

    public void setUserId(ApplicationUser userId) {
        this.userId = userId;
    }

    public Date getRecordedDate() {
        return recordedDate;
    }

    public void setRecordedDate(Date recordedDate) {
        this.recordedDate = recordedDate;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
