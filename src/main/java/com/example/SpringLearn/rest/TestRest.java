package com.example.SpringLearn.rest;

import com.example.SpringLearn.models.User;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestRest {

    @GetMapping
    public User test(@AuthenticationPrincipal User user) {
        return user;
    }

}
