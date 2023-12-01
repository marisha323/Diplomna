package com.example.Diplomna.services;

import com.example.Diplomna.repo.SupportReviewStatusRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SupportReviewStatusService {

    @Autowired
    private final SupportReviewStatusRepo supportReviewStatusRepo;

    public SupportReviewStatusService(SupportReviewStatusRepo supportReviewStatusRepo) {
        this.supportReviewStatusRepo = supportReviewStatusRepo;
    }
}
