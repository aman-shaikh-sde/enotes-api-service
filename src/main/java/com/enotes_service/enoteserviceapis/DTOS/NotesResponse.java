package com.enotes_service.enoteserviceapis.DTOS;

import com.enotes_service.enoteserviceapis.Entity.Notes;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NotesResponse {
    private List<NotesDTO> notes;

    private Integer totalPage;
    private Integer totalElements;
    private Integer pageSize;
    private Integer pageNo;
    private Boolean isFirst;
    private Boolean isLast;
}
;