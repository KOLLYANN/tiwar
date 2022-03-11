package com.example.SpringLearn.controllers;

import com.example.SpringLearn.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/u")
public class UseController {

    @GetMapping("{user}")
    public String user(@PathVariable User user) {
        return "usr";
    }
}
