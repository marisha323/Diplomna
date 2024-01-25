package com.example.Diplomna.repo;

import com.example.Diplomna.model.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VideoRepo extends JpaRepository<Video,Long> {

//    public Optional<InputStream> getPreviewInputStream(Long id);
//
//    public List<VideoMetadataRepr> findAllVideoMetadata();
//    public Optional<VideoMetadataRepr> findById(long id);
//
//    public void saveNewVideo(NewVideoRepr newVideoRepr);

    List<Video> findByVideoCategory_Id(Long videoCategoryId);

    List<Video> findByTitleContaining(String title);

    Optional<Video> findByTitle(String yourPropertyName);

}
