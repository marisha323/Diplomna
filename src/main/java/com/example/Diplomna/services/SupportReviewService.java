package com.example.Diplomna.services;

import com.example.Diplomna.repo.SupportReviewRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SupportReviewService {

    @Autowired
    private final SupportReviewRepo supportReviewRepo;

    public SupportReviewService(SupportReviewRepo supportReviewRepo) {
        this.supportReviewRepo = supportReviewRepo;
    }
}
