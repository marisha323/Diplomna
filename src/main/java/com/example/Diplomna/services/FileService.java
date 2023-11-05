package com.example.Diplomna.services;

import com.example.Diplomna.repo.FileRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FileService {
    private final FileRepo fileRepo;
@Autowired
    public FileService(FileRepo fileRepo) {
        this.fileRepo = fileRepo;
    }
}
