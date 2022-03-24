package com.example.SpringLearn.controllers.clan;

import com.example.SpringLearn.models.clan.Clan;
import com.example.SpringLearn.models.user.User;
import com.example.SpringLearn.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/clan/money")
public class ClanRatingMoneyController {

    final UserService userService;

    @Autowired
    public ClanRatingMoneyController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}/rating")
    public String getRatingMoney(
            @AuthenticationPrincipal User user,
            @PathVariable("id") Long id,
            Model model
    ) {

        User userById = userService.findUserById(user.getId());
        Clan clan = userById.getClan();

        if (userById.getClan().getId() == id) {

            List<User> users = clan.getUsers().stream()
                    .sorted((u1, u2) -> u2.getAmountGoldForClan().compareTo(u1.getAmountGoldForClan()))
                    .limit(15)
                    .toList();

            model.addAttribute("usersRatingGold", users);
            model.addAttribute("us", userById);
            model.addAttribute("clan", clan);
            model.addAttribute("exp", userService.expBar(userService.findUserByIdt(user.getId()).get()));

        } else {
            return "redirect:/";
        }
        return "clan/clanRatingMoney";
    }


}
