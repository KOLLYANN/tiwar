package com.example.SpringLearn.controllers.cave;

import com.example.SpringLearn.models.Boss;
import com.example.SpringLearn.models.user.User;
import com.example.SpringLearn.services.boss.BossService;
import com.example.SpringLearn.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/boss")
public class BossController {

    final UserService userService;
    final BossService bossService;

    @Autowired
    public BossController(UserService userService, BossService bossService) {
        this.userService = userService;
        this.bossService = bossService;
    }

    @GetMapping("/{id}")
    public String getBoss(
            @AuthenticationPrincipal User user,
            @PathVariable("id") Long id,
            Model model

    ) {
        User userById = userService.findUserById(user.getId());

        if (userById.getIdBossAttack() == null) {

            model.addAttribute("us", userById);
            model.addAttribute("exp", userService.expBar(userById));
            if(id == 1) {
                model.addAttribute("boss", bossService.findBossById(1L));
            } else if (id == 2) {
                model.addAttribute("boss", bossService.findBossById(2L));
            }
        } else {
            Boss boss = bossService.findBossById(userById.getIdBossAttack());
            if(userById.getIdBossAttack() == null && !Objects.equals(userById.getIdBossAttack(), id)) {
                return "redirect:/cave";
            }
            model.addAttribute("us", userById);
            model.addAttribute("exp", userService.expBar(userById));
            model.addAttribute("boss", boss);
            if(boss.getMaxHealth() == 50000) {
                model.addAttribute("bossExpBar", Math.floor((double) boss.getHealth() / 500));
            } else if (boss.getMaxHealth() == 100000) {
                model.addAttribute("bossExpBar", Math.floor((double) boss.getHealth() / 1000));

            }
        }

        return "/cave/bossPage";
    }


    @PostMapping("/attack")
    public String attack(
            @AuthenticationPrincipal User user,
            @RequestParam("bossId") Long bossId
    ) {
        User userById = userService.findUserById(user.getId());
        List<User> userList = userById.getClan().getUsers();

        if (userById.getIdBossAttack() == null) {
            Boss boss = null;
            if(bossId == 1) {
               boss = new Boss("Дядя Ваня", 100L, 50000L, 50000L, 1L);
            } else if(bossId == 2) {
                boss = new Boss("Топор", 500L, 100000L, 100000L, 1L);
            }
            bossService.saveBoss(boss);
            for(User us : userList) {
                userService.updateBossAttack(boss.getId(), us.getId());
            }
            return "redirect:/boss/" + boss.getId();
        } else {
            Boss boss = bossService.findBossById(userById.getIdBossAttack());
            if (boss.getHealth() > 0 && userById.getHealth() > 0) {

                bossService.bossOneMinusHealth(userById.getPower(), boss.getId());
                userService.bossAttack(boss.getPower(), userById.getId());

                return "redirect:/boss/" + boss.getId();
            } else {
                if (boss.getHealth() <= 0) {

                    userService.updateToNullBossAttack(userById.getId());
                    bossService.deleteBoss(boss);
                }
                if (userById.getHealth() <= 0) {

                    userService.updateToNullBossAttack(userById.getId());
                }
            }
        }
        return "redirect:/cave";

    }
}
