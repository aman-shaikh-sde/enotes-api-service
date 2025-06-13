package com.enotes_service.enoteserviceapis.DTOS;

import lombok.*;

import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDTO {

    private Integer id;
    private String name;
    private String description;
    private Boolean isActive;
    private Integer createdBy;
    private Date createdDate;
    private Integer updatedBy;
    private Date updatedDate;
}
