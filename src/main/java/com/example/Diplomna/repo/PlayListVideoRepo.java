package com.example.Diplomna.repo;

import com.example.Diplomna.model.PlayList;
import com.example.Diplomna.model.PlayListVideo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PlayListVideoRepo extends JpaRepository<PlayListVideo,Long> {
    @Query("SELECT p.video.id FROM PlayListVideo p WHERE p.playList.id = :playListId AND p.video.id = :videoId")
    PlayListVideo findEntry(Long playListId, Long videoId);

    default boolean isDuplicateEntry(Long playListId, Long videoId) {
        return findEntry(playListId, videoId) != null;
    }
}

