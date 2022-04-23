package com.example.tiwar.controllers.user;

import com.example.tiwar.models.user.User;
import com.example.tiwar.repositories.user.UserRepo;
import com.example.tiwar.services.user.UserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/active")
public class ActivateController {

    final UserService userService;
    final UserRepo userRepo;

    public ActivateController(UserService userService, UserRepo userRepo) {
        this.userService = userService;
        this.userRepo = userRepo;
    }

    @GetMapping("/{code}")
    public String code(
            @AuthenticationPrincipal User user,
            @PathVariable String code) {

        if(user.getActivateMailCode().equals(code) && !user.isActiveCode()) {
            user.setActiveCode(true);
            System.out.println(code);
            userRepo.save(user);
            return "/users/activate";
        }
        return "redirect:" + user.getId() ;
    }

}
