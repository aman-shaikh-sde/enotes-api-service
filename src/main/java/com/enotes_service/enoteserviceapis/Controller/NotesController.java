package com.enotes_service.enoteserviceapis.Controller;

import com.enotes_service.enoteserviceapis.DTOS.CategoryDTO;
import com.enotes_service.enoteserviceapis.DTOS.NotesDTO;
import com.enotes_service.enoteserviceapis.Entity.Category;
import com.enotes_service.enoteserviceapis.Entity.FileDetails;
import com.enotes_service.enoteserviceapis.Entity.Notes;
import com.enotes_service.enoteserviceapis.Service.NotesService;
import com.enotes_service.enoteserviceapis.Service.ServiceImpl.NotesServiceImpl;
import com.enotes_service.enoteserviceapis.Util.CommonUtil;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;


import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collection;
import java.util.List;


@RestController
@RequestMapping("/api/v1/notes")
public class NotesController {


    @Autowired
    private NotesServiceImpl service;

    @PostMapping("/save-notes")
    public ResponseEntity<?> getNotes(@RequestParam(defaultValue = "") String notes , MultipartFile file) throws Exception {
        NotesDTO savedNotes = service.saveNotes(notes,file);
        if (!ObjectUtils.isEmpty(savedNotes)){
            return CommonUtil.createBuildResponseMessage("Notes Saved Successfully", HttpStatus.CREATED, savedNotes);
    }
        return CommonUtil.createErrorResponse("Not Saved",HttpStatus.INTERNAL_SERVER_ERROR);

    }
    @GetMapping("/download/{id}")
    public ResponseEntity<?> downloadFile(@PathVariable Integer id) throws Exception{
        FileDetails fileDetails=service.getFileDetails(id);
        byte[] downloadFile=service.downloadFile(fileDetails);


        HttpHeaders headers = new HttpHeaders();
        String contentType = CommonUtil.getContentType(fileDetails.getOriginalFileName());
        headers.setContentType(MediaType.parseMediaType(contentType));
        headers.setContentDispositionFormData("attachment", fileDetails.getOriginalFileName());

        return ResponseEntity.ok().headers(headers).body(downloadFile);
    }

    @GetMapping("/notes")
    public ResponseEntity<?> getNotes(@RequestParam(value = "pageNumber" ,defaultValue = "0") Integer pageNumber,
                                      @RequestParam(value = "pageSize",defaultValue = "5") Integer pageSize) {

        List<NotesDTO> allNotes = service.getNotes(pageNumber,pageSize);
        if (!CollectionUtils.isEmpty(allNotes)) {
            return CommonUtil.createBuildResponse(allNotes, HttpStatus.OK);
        }
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/notes-user/{id}")
    public ResponseEntity<?> getNotesByUser(@PathVariable Integer id){

        List<NotesDTO> notesDTO=service.getNotesByUser(id);
        if(!CollectionUtils.isEmpty(notesDTO)){
            return CommonUtil.createBuildResponse(notesDTO, HttpStatus.OK);
        }
        return ResponseEntity.noContent().build();

        }

}
