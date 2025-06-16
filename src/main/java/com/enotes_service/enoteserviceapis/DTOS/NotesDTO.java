package com.enotes_service.enoteserviceapis.DTOS;

import com.enotes_service.enoteserviceapis.Entity.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotesDTO {

    private Integer id;
    private String title;
    private String description;
    private CategoryDTO category;
    private Integer createdBy;
    private Date createdDate;
    private Integer updatedBy;
    private Date updatedDate;
    private FileDTO fileDetails;


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class FileDTO {
        private Integer id;
        private String originalFileName;
        private String displayFileName;

    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CategoryDTO {
        private Integer id;
        private String name;
        private String description;
    }

}
