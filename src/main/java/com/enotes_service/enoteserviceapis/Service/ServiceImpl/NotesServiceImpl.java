package com.enotes_service.enoteserviceapis.Service.ServiceImpl;

import com.enotes_service.enoteserviceapis.DTOS.NotesDTO;
import com.enotes_service.enoteserviceapis.Entity.FileDetails;
import com.enotes_service.enoteserviceapis.Entity.Notes;
import com.enotes_service.enoteserviceapis.Repository.FileRepo;
import com.enotes_service.enoteserviceapis.Repository.NotesRepo;
import com.enotes_service.enoteserviceapis.Service.NotesService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FilenameUtils;
import org.aspectj.util.FileUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.FileNameMap;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class NotesServiceImpl implements NotesService {

    @Autowired
    private FileRepo fileRepo;

    @Autowired
    private NotesRepo notesRepo;

    @Autowired
    private ModelMapper mapper;

    @Value("${file.upload.path}")
    private String uploadPath;

    @Override
    public NotesDTO saveNotes(String notes, MultipartFile file) throws Exception {


        ObjectMapper ob = new ObjectMapper();
        NotesDTO notesDTO = ob.readValue(notes, NotesDTO.class);
        FileDetails fileDetails = saveFile(file);
        Notes notesMap = mapper.map(notes, Notes.class);
        if(!ObjectUtils.isEmpty(fileDetails)){
            notesMap.setFileDetails(fileDetails);
        }else{
            notesMap.setFileDetails(null);
        }


        Notes saveNotes = notesRepo.save(notesMap);

        return mapper.map(saveNotes, NotesDTO.class);
    }
    private FileDetails saveFile(MultipartFile file) throws IOException {
        if ( !ObjectUtils.isEmpty(file) && !file.isEmpty()) {
            String originalName = file.getOriginalFilename();  


            if (originalName == null || originalName.isEmpty()) {
                throw new IllegalArgumentException("Uploaded file name is missing");
            }

            FileDetails fileDetails = new FileDetails();
            fileDetails.setOriginalFileName(originalName);
            fileDetails.setDisplayFileName(getOriginalName(originalName));

            String randString = UUID.randomUUID().toString();
            String extension = FilenameUtils.getExtension(originalName);
            String uplodedFileName = randString + "." + extension;

            fileDetails.setUploadFileName(uplodedFileName);
            fileDetails.setFileSize(file.getSize());

            File saveFile = new File(uploadPath);
            if (!saveFile.exists()) {
                saveFile.mkdirs(); // Use mkdirs() in case intermediate dirs are missing
            }

            String storePath = uploadPath + File.separator + uplodedFileName;
            fileDetails.setPath(storePath);

            Long uploaded = Files.copy(file.getInputStream(), Paths.get(storePath));
            if (uploaded != 0) {
                return fileRepo.save(fileDetails);
            }
        }
        return null;
    }

    private String getOriginalName(String originalName) {
        String extension = FilenameUtils.getExtension(originalName);
        String fileName = FilenameUtils.removeExtension(originalName);
        if (fileName.length() > 8) {
            fileName = fileName.substring(0, 7);
        }
        fileName = fileName + "." + extension;
        return fileName;
    }

    @Override
    public List<NotesDTO> getNotes() {

        List<Notes> notes = notesRepo.findAll();
        List<NotesDTO> getNotes = notes.stream().map(note -> mapper.map(note, NotesDTO.class)).toList();


        return getNotes;
    }
}
