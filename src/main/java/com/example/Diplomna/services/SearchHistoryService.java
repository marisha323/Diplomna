package com.example.Diplomna.services;

import com.example.Diplomna.repo.SearchHistoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SearchHistoryService {
    @Autowired
    private final SearchHistoryRepo searchHistoryRepo;

    public SearchHistoryService(SearchHistoryRepo searchHistoryRepo) {
        this.searchHistoryRepo = searchHistoryRepo;
    }
}
