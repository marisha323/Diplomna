package com.example.Diplomna.repo;

import com.example.Diplomna.model.Video;
import com.example.Diplomna.model.VideoCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VideoCategoryRepo extends JpaRepository<VideoCategory,Long> {

}
