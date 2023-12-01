package com.example.Diplomna.services;

import com.example.Diplomna.repo.GradeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GradeService {

    private final GradeRepo gradeRepo;

    @Autowired
    public GradeService(GradeRepo gradeRepo) {
        this.gradeRepo = gradeRepo;
    }
}
