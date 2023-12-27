package com.example.Diplomna.Controller;

import com.example.Diplomna.model.Video;
import com.example.Diplomna.services.VideoCategoryService;
import com.example.Diplomna.services.VideoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/home")
public class HomeController {

    @Autowired
    private VideoCategoryService videoCategoryService;
    @Autowired
    private VideoService videoService;
    private static final Logger logger = LoggerFactory.getLogger(VideoController.class);

    @GetMapping("/sort")
    public ResponseEntity<List<Video>> sortCategory(@RequestParam Long videoCategoryId) {
        // Перевірка наявності категорії
        if (videoCategoryService.existsById(videoCategoryId)) {
            // Отримання всіх відео з даною категорією
            List<Video> videosInCategory = videoService.getVideosByCategoryId(videoCategoryId);
            return new ResponseEntity<>(videosInCategory, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);  // Категорія не знайдена
        }
    }
}
