package com.example.Diplomna.repo;

import com.example.Diplomna.model.PlayList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlayListRepo extends JpaRepository<PlayList,Long> {
    List<PlayList> findByUserId(Long userId);
}
