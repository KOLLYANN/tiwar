package com.example.SpringLearn.controllers.cave;

import com.example.SpringLearn.models.Boss;
import com.example.SpringLearn.models.clan.Clan;
import com.example.SpringLearn.models.user.User;
import com.example.SpringLearn.services.boss.BossService;
import com.example.SpringLearn.services.clan.ClanService;
import com.example.SpringLearn.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/boss")
public class BossController {

    final UserService userService;
    final BossService bossService;
    final ClanService clanService;

    @Autowired
    public BossController(UserService userService, BossService bossService, ClanService clanService) {
        this.userService = userService;
        this.bossService = bossService;
        this.clanService = clanService;
    }

    @GetMapping("/{id}")
    public String getBoss(
            @AuthenticationPrincipal User user,
            @PathVariable("id") Long id,
            Model model

    ) {
        User userById = userService.findUserById(user.getId());

        if (bossService.existsBoss(id)) {

            if (userById.getIdBossAttack() == null) {

                model.addAttribute("us", userById);
                model.addAttribute("exp", userService.expBar(userById));
                if (id == 1) {
                    model.addAttribute("boss", bossService.findBossById(1L));
                    model.addAttribute("startAttack", 1);
                } else if (id == 2) {
                    model.addAttribute("boss", bossService.findBossById(2L));
                    model.addAttribute("startAttack", 1);
                } else if (id == 3) {
                    model.addAttribute("boss", bossService.findBossById(3L));
                    model.addAttribute("startAttack", 1);
                } else if (id == 4) {
                    model.addAttribute("boss", bossService.findBossById(4L));
                    model.addAttribute("startAttack", 1);
                } else if (id == 5) {
                    model.addAttribute("boss", bossService.findBossById(5L));
                    model.addAttribute("startAttack", 1);
                }
            } else {
                Boss boss = bossService.findBossById(userById.getIdBossAttack());

                List<User> users = userById.getClan().getUsers();
                List<User> usersDamage = new ArrayList<>();
                for (User us : users) {
                    if (us.getBossDamage() != null) {
                        usersDamage.add(us);
                    }
                }
                List<User> collect;
                collect = usersDamage.stream().sorted((u1, u2) -> u2.getBossDamage()
                                .compareTo(u1.getBossDamage())).limit(3)
                        .collect(Collectors.toList());

                if(userService.expBar(userById) == 100) {
                    model.addAttribute("newLevel", 1);
                }
                userService.updateLevelUser(userById);

                model.addAttribute("us", userById);
                model.addAttribute("exp", userService.expBar(userById));
                model.addAttribute("boss", boss);
                model.addAttribute("topDamage", collect);
                if (boss.getMaxHealth() == 50000) {
                    model.addAttribute("bossExpBar", Math.floor((double) boss.getHealth() / 500));
                } else if (boss.getMaxHealth() == 100000) {
                    model.addAttribute("bossExpBar", Math.floor((double) boss.getHealth() / 1000));

                } else if (boss.getMaxHealth() == 250000) {
                    model.addAttribute("bossExpBar", Math.floor((double) boss.getHealth() / 2500));

                } else if (boss.getMaxHealth() == 500000) {
                    model.addAttribute("bossExpBar", Math.floor((double) boss.getHealth() / 5000));

                } else if (boss.getMaxHealth() == 1000000) {
                    model.addAttribute("bossExpBar", Math.floor((double) boss.getHealth() / 10000));
                }
            }

        } else {
            return "redirect:/cave";
        }

        return "/cave/bossPage";
    }


    @PostMapping("/attack")
    public String attack(
            @AuthenticationPrincipal User user,
            @RequestParam("bossId") Long bossId
    ) {

        if (!bossService.existsBoss(bossId)) {
            return "redirect:/cave";
        }

        User userById = userService.findUserById(user.getId());
        List<User> userList = userById.getClan().getUsers();

        if (userById.getIdBossAttack() == null) {
            Boss boss = null;
            if (bossId == 1) {
                boss = new Boss("Дядя Ваня", 100L, 50000L, 50000L, 1L, "1");
            } else if (bossId == 2) {
                boss = new Boss("Топор", 500L, 100000L, 100000L, 1L, "2");
            } else if (bossId == 3) {
                boss = new Boss("Монгол", 1000L, 250000L, 250000L, 1L, "4");
            } else if (bossId == 4) {
                boss = new Boss("Авторитет", 3000L, 500000L, 500000L, 1L, "3");
            } else if (bossId == 5) {
                boss = new Boss("Поль", 5000L, 1000000L, 1000000L, 1L, "5");
            }

            bossService.saveBoss(boss);
            for (User us : userList) {
                userService.updateBossAttack(boss.getId(), us.getId());
                userService.updateBossDamage(0L, us.getId());
            }
            return "redirect:/boss/" + boss.getId();
        } else {
            Boss boss = bossService.findBossById(userById.getIdBossAttack());
            if (boss.getHealth() > 0 && userById.getHealth() > 0) {

                bossService.bossOneMinusHealth(userById.getPower(), boss.getId());
                userService.bossAttack(boss.getPower(), userById.getId());
                userService.bossDamage(userById.getPower(), userById.getId());

                return "redirect:/boss/" + boss.getId();
            } else {
                if (boss.getHealth() <= 0) {
                    Clan clan = userById.getClan();
                    if(boss.getPower() == 100) {
                        clanService.addCountB1forClan(clan.getId());
                    } else if(boss.getPower() == 500) {
                        clanService.addCountB2forClan(clan.getId());
                    } else if(boss.getPower() == 1000) {
                        clanService.addCountB3forClan(clan.getId());
                    } else if(boss.getPower() == 3000) {
                        clanService.addCountB4forClan(clan.getId());
                    } else if(boss.getPower() == 5000) {
                        clanService.addCountB5forClan(clan.getId());
                    }

                    for (User us : userList) {
                        if(us.getBossDamage() != null) {
                            if (us.getBossDamage() > 0) {
                                if (boss.getPower() == 100) {
                                    userService.updateExpAndSilverUserAtCave(100L, 50L, us.getId());
                                } else if (boss.getPower() == 500) {
                                    userService.updateExpAndSilverUserAtCave(500L, 250L, us.getId());
                                } else if (boss.getPower() == 1000) {
                                    userService.updateExpAndSilverUserAtCave(1000L, 5000L, us.getId());
                                } else if (boss.getPower() == 3000) {
                                    userService.updateExpAndSilverUserAtCave(3000L, 15000L, us.getId());
                                } else if (boss.getPower() == 5000) {
                                    userService.updateExpAndSilverUserAtCave(5000L, 25000L, us.getId());
                                }
                            }
                        }
                        userService.updateToNullBossAttack(us.getId());
                    }

                    bossService.deleteBoss(boss);


                }
            }
        }
        return "redirect:/cave";

    }
}
