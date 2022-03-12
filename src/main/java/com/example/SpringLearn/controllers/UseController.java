package com.example.SpringLearn.controllers;

import com.example.SpringLearn.models.User;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/u")
public class UseController {

    @GetMapping("{user}")
    public String user(Model model, @AuthenticationPrincipal User user) {
        String active;
        if(user.isActiveCode()) {
            active = "Активирован";
        } else {
            active = "Не активирован";
        }
        model.addAttribute("code",  active);
        return "usr";
    }
}
