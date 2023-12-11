package com.example.Diplomna.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/video")
public class VideoController {
    @GetMapping("/add")
    public String addCategory(Model model)
    {
        return "rdfigjrdifjhidh";
    }
}
