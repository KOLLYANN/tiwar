package com.example.SpringLearn.controllers.clan;

import com.example.SpringLearn.models.clan.Clan;
import com.example.SpringLearn.models.user.User;
import com.example.SpringLearn.services.clan.ClanService;
import com.example.SpringLearn.services.user.UserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/clanrating")
public class RatingClanController {

    final ClanService clanService;
    final UserService userService;

    public RatingClanController(ClanService clanService, UserService userService) {
        this.clanService = clanService;
        this.userService = userService;
    }


    @GetMapping
    public String getAllClans(
            @AuthenticationPrincipal User user,
            Model model
    ) {
        User us = userService.findUserById(user.getId());

        List<Clan> allClans = clanService.findAllClans();
        allClans.sort((c1, c2) -> c2.getLevel().compareTo(c1.getLevel()));
        model.addAttribute("clans", allClans);
        model.addAttribute("us", us);
        model.addAttribute("exp", userService.expBar(us));

        return "clan/ratingClans";
    }


}
