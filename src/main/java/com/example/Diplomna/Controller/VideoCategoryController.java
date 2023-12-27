package com.example.Diplomna.Controller;

import com.example.Diplomna.GrabePicture.VideoMetadataRepr;
import com.example.Diplomna.classValid.VideoCategoryCrm;
import com.example.Diplomna.services.VideoCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/category")
public class VideoCategoryController {
    @Autowired
    private VideoCategoryService videoCategoryService;

    public VideoCategoryController() {

    }

    @GetMapping("/all-category")
    public List<VideoCategoryCrm> findAll()
    {
        return videoCategoryService.findAll();
    }
    @GetMapping("/{id}")
    public VideoCategoryCrm findVideoCategoryById(@PathVariable("id") Long id) {
        return videoCategoryService.findById(id).orElseThrow(NotFoundException::new);
    }

}
