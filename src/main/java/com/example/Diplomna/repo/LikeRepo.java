package com.example.Diplomna.repo;

import com.example.Diplomna.model.VideoLike;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepo extends JpaRepository<VideoLike,Long> {
}
