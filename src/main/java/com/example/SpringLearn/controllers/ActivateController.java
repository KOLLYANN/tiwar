package com.example.SpringLearn.controllers;

import com.example.SpringLearn.models.User;
import com.example.SpringLearn.services.UserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/active")
public class ActivateController {

    final UserService userService;

    public ActivateController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{code}")
    public String code(
            @AuthenticationPrincipal User user,
            @PathVariable String code) {

        if(user.getActivateMailCode().equals(code) && !user.isActiveCode()) {
            user.setActiveCode(true);
            System.out.println(code);
            userService.saveUser(user);
            return "activate";
        }
        return "redirect:/u/" + user.getId() ;
    }

}
