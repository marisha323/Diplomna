package com.example.Diplomna.Controller;

import com.example.Diplomna.classValid.AccessStatusCrm;
import com.example.Diplomna.classValid.VideoCategoryCrm;
import com.example.Diplomna.services.AccessStatusService;
import com.example.Diplomna.services.VideoCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/access")
public class AccessStatusController {
    @Autowired
    private AccessStatusService accessStatusService ;

    public AccessStatusController() {

    }

    @GetMapping("/all-status")
    public List<AccessStatusCrm> findAll()
    {
        return accessStatusService.findAll();
    }
    @GetMapping("/{id}")
    public AccessStatusCrm findVideoCategoryById(@PathVariable("id") Long id) {
        return accessStatusService.findById(id).orElseThrow(NotFoundException::new);
    }
}
