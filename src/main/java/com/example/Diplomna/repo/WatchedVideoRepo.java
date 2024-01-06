package com.example.Diplomna.repo;

import com.example.Diplomna.model.WatchedVideo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface WatchedVideoRepo extends JpaRepository<WatchedVideo,Long> {
    List<WatchedVideo> findByVideoId(Long videoId);
    @Query("SELECT COUNT(wv.grade) FROM WatchedVideo wv WHERE wv.video.id = :videoId AND wv.grade.id = 1")
    long countGradeLikeForVideoId(@Param("videoId") Long videoId);

    @Query("SELECT COUNT(wv.grade) FROM WatchedVideo wv WHERE wv.video.id = :videoId AND wv.grade.id = 2")
    long countGradeDislikeForVideoId(@Param("videoId") Long videoId);


    @Query("SELECT COUNT(wv.watchCount) FROM WatchedVideo wv WHERE wv.video.id = :videoId ")
    long countwatchForVideoId(@Param("videoId") Long videoId);
}
