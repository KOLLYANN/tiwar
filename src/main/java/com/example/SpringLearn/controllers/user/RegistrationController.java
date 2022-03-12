package com.example.SpringLearn.controllers.user;

import com.example.SpringLearn.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/registration")
public class RegistrationController {

    final UserService userService;

    @Autowired
    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String registration() {
        return "/users/registration";
    }

    @PostMapping
    public String addUser(@RequestParam String email, @RequestParam String username, @RequestParam String password, Model model) {
        if(!userService.checkUsername(email)) {
            userService.addUser(email,username, password);
            return "redirect:/login";
        }
        model.addAttribute("userExists","Пользователь существует");
        return "/users/registration";
    }

}
