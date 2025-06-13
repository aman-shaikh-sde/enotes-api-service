package com.enotes_service.enoteserviceapis.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

@Data
@MappedSuperclass
public abstract class BaseModel  {

    @CreatedBy
    @Column(updatable = false)
    private Integer createdBy;

    @CreatedDate
    @Column(updatable = false)
    private Date createdDate;

    @LastModifiedBy
    @Column(insertable = false)
    private Integer updatedBy;

    @LastModifiedDate
    @Column(insertable = false)
    private Date updatedDate;
}
