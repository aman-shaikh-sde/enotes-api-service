package com.enotes_service.enoteserviceapis.Controller;

import com.enotes_service.enoteserviceapis.DTOS.CategoryDTO;
import com.enotes_service.enoteserviceapis.DTOS.NotesDTO;
import com.enotes_service.enoteserviceapis.Entity.Category;
import com.enotes_service.enoteserviceapis.Entity.Notes;
import com.enotes_service.enoteserviceapis.Service.NotesService;
import com.enotes_service.enoteserviceapis.Service.ServiceImpl.NotesServiceImpl;
import com.enotes_service.enoteserviceapis.Util.CommonUtil;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    @GetMapping("/notes")
    public ResponseEntity<?> getNotes() {
        List<NotesDTO> allNotes = service.getNotes();
        if (!CollectionUtils.isEmpty(allNotes)) {
            return CommonUtil.createBuildResponse(allNotes, HttpStatus.OK);
        }
        return ResponseEntity.noContent().build();
    }
}
