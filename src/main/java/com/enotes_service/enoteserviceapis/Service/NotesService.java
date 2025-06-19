package com.enotes_service.enoteserviceapis.Service;

import com.enotes_service.enoteserviceapis.DTOS.NotesDTO;
import com.enotes_service.enoteserviceapis.DTOS.NotesResponse;
import com.enotes_service.enoteserviceapis.Entity.FileDetails;
import com.enotes_service.enoteserviceapis.Entity.Notes;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface NotesService {

    public NotesDTO saveNotes(String notes, MultipartFile file)throws Exception;

    public List<NotesDTO> getNotes(Integer pageNumber,Integer pageSize);



   public byte[] downloadFile(FileDetails fileDetails) throws Exception;

   public FileDetails getFileDetails(Integer id) throws Exception;

    NotesResponse getAllNotesByUser(Integer userId, Integer pageNo, Integer pageSize);
}
