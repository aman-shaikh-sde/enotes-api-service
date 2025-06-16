package com.enotes_service.enoteserviceapis.Service;

import com.enotes_service.enoteserviceapis.DTOS.NotesDTO;
import com.enotes_service.enoteserviceapis.Entity.Notes;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface NotesService {

    public NotesDTO saveNotes(String notes, MultipartFile file)throws Exception;
    public List<NotesDTO> getNotes();
}
