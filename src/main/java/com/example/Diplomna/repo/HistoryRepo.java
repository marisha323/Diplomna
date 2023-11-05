package com.example.Diplomna.repo;

import com.example.Diplomna.model.History;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HistoryRepo extends JpaRepository<History,Long> {
}
