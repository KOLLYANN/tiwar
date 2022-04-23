package com.example.tiwar.controllers;

import com.example.tiwar.models.user.User;
import com.example.tiwar.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class IndexController {

    final UserService userService;

    @Autowired
    public IndexController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String index(@AuthenticationPrincipal User user, Model model) {
        User u = userService.findUserById(user.getId());
        model.addAttribute("us", u);
        model.addAttribute("exp", userService.expBar(u));

        return "parts/index";
    }

}
