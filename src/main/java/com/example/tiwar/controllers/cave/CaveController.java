package com.example.tiwar.controllers.cave;

import com.example.tiwar.models.user.User;
import com.example.tiwar.services.clan.ClanService;
import com.example.tiwar.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/cave")
public class CaveController {

    final UserService userService;
    final ClanService clanService;

    @Autowired
    public CaveController(UserService userService, ClanService clanService) {
        this.userService = userService;
        this.clanService = clanService;
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

        if(userService.expBar(userById) == 100) {
            model.addAttribute("newLevel", 1);
        }
        clanService.updateLevelClan(userById.getClan().getId());
        userService.updateLevelUser(userById);

        model.addAttribute("us", userById);
        model.addAttribute("exp", userService.expBar(userById));
        model.addAttribute("newLevel", 1);

        return "cave/cave";
    }





}
