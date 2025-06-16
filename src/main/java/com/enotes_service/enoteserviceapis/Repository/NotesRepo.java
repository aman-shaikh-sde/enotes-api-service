package com.enotes_service.enoteserviceapis.Repository;

import com.enotes_service.enoteserviceapis.Entity.Notes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotesRepo extends JpaRepository<Notes,Integer> {
}
