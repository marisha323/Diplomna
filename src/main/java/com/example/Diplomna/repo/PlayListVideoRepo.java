package com.example.Diplomna.repo;

import com.example.Diplomna.model.PlayList;
import com.example.Diplomna.model.PlayListVideo;
import com.example.Diplomna.model.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PlayListVideoRepo extends JpaRepository<PlayListVideo,Long> {
    @Query("SELECT p.video.id FROM PlayListVideo p WHERE p.playList.id = :playListId AND p.video.id = :videoId")
    PlayListVideo findEntry(Long playListId, Long videoId);

    default boolean isDuplicateEntry(Long playListId, Long videoId) {
        return findEntry(playListId, videoId) != null;
    }
    List<PlayListVideo> findAllByPlayListId(Long playListId);
    @Query("SELECT pv FROM PlayListVideo pv WHERE pv.playList = :playlistId")
    List<PlayListVideo> findByPlayListId(Long playlistId);
}

