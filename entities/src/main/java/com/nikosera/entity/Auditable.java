package com.nikosera.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

/**
 * @author Narayan Joshi
 * <narenzoshi@gmail.com>
 */
@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class Auditable<T> {

    @CreationTimestamp
    @Column(name = "CREATED_DATE", nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @CreatedBy
    @JoinColumn(name = "CREATED_BY", referencedColumnName = "ID", nullable = false, updatable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private T createdBy;

    @LastModifiedDate
    @Column(name = "LAST_MODIFIED_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;

    @JsonBackReference
    @LastModifiedBy
    @JoinColumn(name = "LAST_MODIFIED_BY", referencedColumnName = "ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private T lastModifiedBy;

}
