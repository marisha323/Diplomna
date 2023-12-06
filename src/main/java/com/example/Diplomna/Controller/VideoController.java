package com.example.Diplomna.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/video")
public class VideoController {
    @GetMapping("/add")
    public String addCategory(Model model)
    {
        return "rdfigjrdifjhidh";
    }

    @GetMapping("/info")
    public String videoData(Principal principal) //інжектимо поточного користувача через принципал
    {
        return principal.getName();
    }
    @GetMapping("/unsecured")
    public String unsecuredData() {
        return "Unsecured data";
    }

    @GetMapping("/secured")
    public String securedData() {
        return "Secured data";
    }

    @GetMapping("/admin")
    public String adminData() {
        return "Admin data";
    }

    @GetMapping("/info")
    public String userData(Principal principal) {
        return principal.getName();
    }
}
