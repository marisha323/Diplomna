package com.example.Diplomna.repo;

import com.example.Diplomna.model.File;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepo extends JpaRepository<File,Long> {
}
