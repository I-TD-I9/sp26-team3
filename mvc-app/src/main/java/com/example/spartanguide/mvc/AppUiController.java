package com.example.spartanguide.mvc;

import com.example.spartanguide.service.TourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AppUiController {

    @Autowired
    private TourService tourService;

    @GetMapping({"/", "/home"})
    public String home(Model model) {
        model.addAttribute("tours", tourService.getAllTours());
        return "home";
    }

    @GetMapping("/signin")
    public String showSignIn() {
        return "signin";
    }

    @GetMapping("/signup")
    public String showSignUp() {
        return "signup";
    }
}
