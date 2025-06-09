package com.enotes_service.enoteserviceapis.Repository;

import com.enotes_service.enoteserviceapis.Entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepo extends JpaRepository<Category,Integer> {
    List<Category> findByisActiveTrue();
}
