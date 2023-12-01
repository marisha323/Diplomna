package com.example.Diplomna.repo;

import com.example.Diplomna.model.Grade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GradeRepo extends JpaRepository<Grade,Long> {
}
