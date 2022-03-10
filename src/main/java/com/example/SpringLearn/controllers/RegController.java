package com.example.SpringLearn.controllers;

import com.example.SpringLearn.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/registration")
public class RegController {

    @Autowired
    UserService userService;

    @GetMapping
    public String reg() {
        return "reg";
    }

    @PostMapping
    public String addUser(@RequestParam String username, @RequestParam String password, Model model) {
        if(userService.findUser(username) != null) {
            userService.addUser(username, password);
            return "redirect:/login";
        }
        model.addAttribute("userExists","Пользователь существует");
        return "reg";
    }

}
