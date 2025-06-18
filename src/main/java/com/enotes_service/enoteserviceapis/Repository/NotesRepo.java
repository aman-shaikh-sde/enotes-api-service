package com.enotes_service.enoteserviceapis.Repository;

import com.enotes_service.enoteserviceapis.Entity.Notes;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotesRepo extends JpaRepository<Notes,Integer> {
    List<Notes> findByCreatedBy(Integer id);
}
