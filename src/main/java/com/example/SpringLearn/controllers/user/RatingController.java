package com.example.SpringLearn.controllers.user;


import com.example.SpringLearn.models.user.User;
import com.example.SpringLearn.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/rating")
public class RatingController {

    final UserService userService;

    @Autowired
    public RatingController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String rating(Model model,
                                @AuthenticationPrincipal User user) {
        List<User> userList = userService.findAll();
        userList.sort((u1, u2) -> u2.getSkill().compareTo(u1.getSkill()));
        List<User> collect = userList.stream().limit(10).collect(Collectors.toList());

        User user1 = userService.findUserById(user.getId());
        int position = userList.indexOf(user1);


        model.addAttribute("profile", collect);
        model.addAttribute("us", user1);
        model.addAttribute("position", position);
        model.addAttribute("exp", userService.expBar(user1));
        return "users/rating";
    }
}
