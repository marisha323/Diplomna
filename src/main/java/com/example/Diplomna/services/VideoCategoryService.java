package com.example.Diplomna.services;

import com.example.Diplomna.GrabePicture.VideoMetadataRepr;
import com.example.Diplomna.classValid.VideoCategoryCrm;
import com.example.Diplomna.model.Video;
import com.example.Diplomna.model.VideoCategory;
import com.example.Diplomna.repo.VideoCategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VideoCategoryService {

    @Autowired
    private final VideoCategoryRepo videoCategoryRepo;
    @Autowired
    public VideoCategoryService(VideoCategoryRepo videoCategoryRepo) {

        this.videoCategoryRepo = videoCategoryRepo;
    }

    private static VideoCategoryCrm convert(VideoCategory video) {
        VideoCategoryCrm repr = new VideoCategoryCrm();

        repr.setId(video.getId());
        repr.setTitle(video.getTitle());
        return repr;
    }

    public List<VideoCategoryCrm> findAll() {
        return videoCategoryRepo.findAll().stream()
                .map(VideoCategoryService::convert)
                .collect(Collectors.toList());
    }

    public Optional<VideoCategoryCrm> findById(Long id) {
        return videoCategoryRepo.findById(id)
                .map(VideoCategoryService::convert);
    }

}
