package com.example.Diplomna.repo;

import com.example.Diplomna.model.VideoCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VideoCategoryRepo extends JpaRepository<VideoCategory,Long> {
}
