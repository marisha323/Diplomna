package com.example.Diplomna.repo;

import com.example.Diplomna.model.PlayListVideo;
import com.example.Diplomna.model.Video;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
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

    @Query("SELECT v FROM Video v WHERE v.ownerId.id IN :userIds AND DATE(v.uploadDate) BETWEEN :startDate AND :endDate")
    List<Video> findVideosByUserIdsAndDateRange(@Param("userIds") List<Long> userIds, @Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);
    List<Video> findByOwnerId_Id(Long ownerId);
    @Query("SELECT COUNT(v.id) FROM Video v WHERE v.ownerId.id = :userId")
    long countVideoId(@Param("userId") Long userId);


    @Query("SELECT v.title,v.description,v.path FROM Video v WHERE v.id = :videoId")
    Video findByIdWithDetails(@Param("videoId") Long videoId);

}
