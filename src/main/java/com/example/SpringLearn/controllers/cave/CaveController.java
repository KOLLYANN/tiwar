package com.example.SpringLearn.controllers.cave;

import com.example.SpringLearn.models.user.User;
import com.example.SpringLearn.services.user.UserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/cave")
public class CaveController {

    final UserService userService;

    public CaveController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping
    public String getCave(
            @AuthenticationPrincipal User user,
            Model model

    ) {
        User userById = userService.findUserById(user.getId());
        if(userById.getIdBossAttack() != null) {

            return "redirect:/boss/" + userById.getIdBossAttack();
        }
        List<User> users = userById.getClan().getUsers();
        for(User us : users) {
            if(us.getIdBossAttack() != null) {
                userService.updateBossAttack(us.getIdBossAttack(), us.getId());
                return "redirect:/boss/" + us.getIdBossAttack();
            }
        }


        model.addAttribute("us", userById);
        model.addAttribute("exp", userService.expBar(userById));

        return "cave/cave";
    }





}
