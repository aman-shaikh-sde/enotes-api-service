package com.enotes_service.enoteserviceapis.Repository;

import com.enotes_service.enoteserviceapis.Entity.Notes;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotesRepo extends JpaRepository<Notes,Integer> {
    Page<Notes> findByCreatedBy(Integer userId, Pageable pageable);}
