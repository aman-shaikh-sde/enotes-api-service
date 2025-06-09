package com.enotes_service.enoteserviceapis.DTOS;

import lombok.*;

import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDTO {

    private int id;
    private String name;
    private String description;
    private boolean isActive;
    private int createdBy;
    private Date createdDate;
    private int updatedBy;
    private Date updatedDate;
}
