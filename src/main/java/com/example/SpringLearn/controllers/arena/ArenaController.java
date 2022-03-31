package com.example.SpringLearn.controllers.arena;

import com.example.SpringLearn.models.clan.Clan;
import com.example.SpringLearn.models.user.User;
import com.example.SpringLearn.services.clan.ClanService;
import com.example.SpringLearn.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Random;

@Controller
@RequestMapping("/arena")
public class ArenaController {

    final UserService userService;
    final ClanService clanService;

    @Autowired
    public ArenaController(UserService userService, ClanService clanService) {
        this.userService = userService;
        this.clanService = clanService;
    }

    @GetMapping
    public String getArena(
            @AuthenticationPrincipal User us,
        Model model
    ) {
        User userById = userService.findUserById(us.getId());

        model.addAttribute("us", userById);

        int min = (int) ((us.getPower() + us.getMaxMana() + us.getMaxHealth()) - 100);
        int max = (int) ((us.getPower() + us.getMaxMana() + us.getMaxHealth()) + 100);

        int powerOpponent = new Random().nextInt((max - min) + 1) + min;

        String pathToAvaBoss;
        boolean bossAva = new Random().nextBoolean();
        if(bossAva) {
            pathToAvaBoss = "boss.jpg";
        } else {
            pathToAvaBoss = "boss2.jpg";
        }

        model.addAttribute("powerOpponent", powerOpponent);
        model.addAttribute("boss", pathToAvaBoss);
        model.addAttribute("exp", userService.expBar(userById));

        if(userService.expBar(userById) == 100) {
            model.addAttribute("newLevel", 1);
        }

        return "arena/arena";
    }


    @PostMapping("/attack")
    public String addCount(
            @AuthenticationPrincipal User user,
            RedirectAttributes red
    ) {
        User userById = userService.findUserById(user.getId());
        userService.updateLevelUser(userById);
        if(userById.getClan() != null) {
            Clan clan = userById.getClan();
            clanService.updateLevelClan(clan.getId());
        }
        int random = rand();

        int min = (int) (userById.getUserLevel() * 20);
        int max = (int) (userById.getUserLevel() * 25);

        int addSilverRandom = new Random().nextInt((max - min) + 1) + min;

        int min1 = (int) (15);
        int max1 = (int) (20);

        int addExpRandom = new Random().nextInt((max1 - min1) + 1) + min1;

        if(userById.getMana() > 75 && userById.getHealth() > 75) {
            if (random == 1 || random == 0) {
                userService.addExpInRating(addExpRandom,addExpRandom,addSilverRandom,userById.getId());
                if(userById.getClan() != null) {
                    userService.addExpInClan(userById, (long) addExpRandom);
                }
                userService.minusUserManaAndHealthByVictory(userById.getId());
                red.addFlashAttribute("random2", random);
                red.addFlashAttribute("notManaOrHealth", 1);
                red.addFlashAttribute("addSilver", addSilverRandom);
                red.addFlashAttribute("addExp", addExpRandom);

            } else {
                red.addFlashAttribute("random2", random);
                userService.minusUserManaAndHealthByDefeat(userById.getId());
                red.addFlashAttribute("notManaOrHealth", 1);

            }
        } else {
            red.addFlashAttribute("notManaOrHealth", 0);
        }
        return "redirect:/arena";
    }

    public static int rand() {
        return new Random().nextInt(3);
    }
}
