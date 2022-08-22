package com.nasr.supportingsystemproject.base.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.persistence.Id;
import java.io.Serializable;
import java.time.LocalDateTime;

@MappedSuperclass
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public  class BaseEntity <ID extends Serializable,U extends CharSequence>{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected ID id;

    @Column
    protected Boolean isDeleted;

    @CreatedBy
    protected U createdBy;

    @CreatedDate
    protected LocalDateTime createdDate;

    @LastModifiedBy
    protected U lastModifiedBy;

    @LastModifiedDate
    protected LocalDateTime lastModifiedDate;

    public BaseEntity(ID id) {
        this.id = id;
    }
}
