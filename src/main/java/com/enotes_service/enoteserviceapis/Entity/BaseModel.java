package com.enotes_service.enoteserviceapis.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;

import java.util.Date;

@Data
@MappedSuperclass
public class BaseModel  {

    private boolean isActive;
    private boolean isDeleted;
    private int createdBy;
    private Date createdDate;
    private int updatedBy;
    private Date updatedDate;
}
