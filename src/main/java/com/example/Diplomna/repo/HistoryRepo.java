package com.example.Diplomna.repo;

import com.example.Diplomna.model.History;
import com.example.Diplomna.model.PlayList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HistoryRepo extends JpaRepository<History,Long> {
    List<History> findByUserId(Long userId);
}
