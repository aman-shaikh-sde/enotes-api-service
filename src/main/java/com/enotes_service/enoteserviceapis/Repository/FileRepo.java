package com.enotes_service.enoteserviceapis.Repository;

import com.enotes_service.enoteserviceapis.Entity.FileDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.File;

public interface FileRepo extends JpaRepository<FileDetails,Integer> {
}
