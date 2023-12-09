package com.example.Diplomna.services;

import com.example.Diplomna.repo.VideoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class VideoService {
    private final VideoRepo videoRepo;
@Autowired
    public VideoService(VideoRepo videoRepo) {
        this.videoRepo = videoRepo;
    }

    public void uploadVideo(MultipartFile multipartFile)
    {
        //Upload file
        //save video to DB
    }
}
