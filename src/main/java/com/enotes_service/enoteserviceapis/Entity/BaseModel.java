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
public class BaseModel  {

    private boolean isActive;
    private boolean isDeleted;
    @CreatedBy
    private int createdBy;
    @CreatedDate
    private Date createdDate;
    @LastModifiedBy
    private int updatedBy;
    @Column(insertable = false)
    @LastModifiedDate
    private Date updatedDate;
}
