package com.example.Diplomna.services;

import com.example.Diplomna.classValid.CrmHelper;
import com.example.Diplomna.model.History;
import com.example.Diplomna.model.PlayList;
import com.example.Diplomna.repo.HistoryRepo;
import com.example.Diplomna.repo.UserRepo;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HistoryService {
    private final HistoryRepo historyRepo;
    private final Logger logger = LoggerFactory.getLogger(PlayListService.class);
    private UserRepo userRepo;
    @Autowired
    public HistoryService(HistoryRepo historyRepo, UserRepo userRepo) {
        this.historyRepo = historyRepo;
        this.userRepo = userRepo;
    }
    @Transactional
    public List<History> getHistoryListByUserId(String authorizationHeader) {
        CrmHelper crmHelper = new CrmHelper(userRepo);
        Long userId = crmHelper.userId(authorizationHeader);
        logger.info("userId " + userId);

        if (userId != null) {
            return historyRepo.findByUserId(userId);
        } else {
            throw new IllegalArgumentException("Невірний ідентифікатор користувача");
        }
    }

}
