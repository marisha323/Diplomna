package com.example.Diplomna.repo;

import com.example.Diplomna.model.PlayList;
import com.example.Diplomna.model.PlayListVideo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayListVideoRepo extends JpaRepository<PlayListVideo,Long> {
}

