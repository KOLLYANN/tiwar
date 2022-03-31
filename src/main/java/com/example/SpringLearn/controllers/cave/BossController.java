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

import java.util.*;
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
                getBossForStartAttack(id, model);

            } else {
                if (bossService.findBossById(userById.getIdBossAttack()).isPresent()) {
                    Boss boss = bossService.findBossById(userById.getIdBossAttack()).get();

                    List<User> users = userById.getClan().getUsers();
                    Set<User> usersDamage = new HashSet<>();
                    for (User us : users) {
                        if (us.getBossDamage() != null) {
                            if (us.getBossDamage() > 0) {
                                System.out.println(us.getId());
                                usersDamage.add(us);
                            }
                        }
                    }
                    List<User> collect;
                    collect = usersDamage.stream().sorted((u1, u2) -> u2.getBossDamage()
                                    .compareTo(u1.getBossDamage())).limit(10)
                            .collect(Collectors.toList());

                    if (userService.expBar(userById) == 100) {
                        model.addAttribute("newLevel", 1);
                    }
                    userService.updateLevelUser(userById);

                    model.addAttribute("us", userById);
                    model.addAttribute("exp", userService.expBar(userById));
                    model.addAttribute("boss", boss);
                    model.addAttribute("topDamage", collect);

                    model.addAttribute("time", String.valueOf((boss.getTime() - new Date().getTime()) / 1000));

                    bossHealth(model, boss);

                } else {
                    return "redirect:/cave";
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
            @RequestParam("bossId") String bossIds
    ) {

        String s = bossIds.replaceAll("[^\\d]", "");
        long bossId = Long.parseLong(s);

        if (!bossService.existsBoss(bossId)) {
            return "redirect:/cave";
        }

        User userById = userService.findUserById(user.getId());
        List<User> userList = userById.getClan().getUsers();

        if (userById.getIdBossAttack() == null) {
            Boss boss = null;

            boss = getBoss(bossId, boss);

            if (boss != null) {
                bossService.saveBoss(boss);
            } else {
                return "redirect:/cave";
            }
            for (User us : userList) {
                userService.updateBossAttack(boss.getId(), us.getId());
                userService.updateBossDamage(0L, us.getId());
            }
            return "redirect:/boss/" + boss.getId();
        } else {
            if (bossService.findBossById(userById.getIdBossAttack()).isPresent()) {
                Boss boss = bossService.findBossById(userById.getIdBossAttack()).get();

                if (boss.getTime() < new Date().getTime()) {
                    for (User us : userList) {
                        if (us.getBossDamage() != null) {
                            userService.updateToNullBossAttack(us.getId());
                        }
                    }
                    return "redirect:/cave";
                }

                if (boss.getHealth() > 0 && userById.getHealth() > 0 && boss.getTime() > new Date().getTime()) {

                    bossService.bossOneMinusHealth(userById.getPower(), boss.getId());
                    userService.bossAttack(boss.getPower(), userById.getId());
                    userService.bossDamage(userById.getPower(), userById.getId());

                    return "redirect:/boss/" + boss.getId();
                } else {
                    if (boss.getHealth() <= 0 && boss.getTime() > new Date().getTime()) {
                        Clan clan = userById.getClan();

                        adClanTrophies(boss, clan);

                        for (User us : userList) {
                            if (us.getBossDamage() != null) {
                                if (us.getBossDamage() > 0) {
                                    addExpAndSilverForUser(boss, us);
                                }
                            }
                            userService.updateToNullBossAttack(us.getId());
                        }
//                                bossService.deleteBoss(boss);
                    }
                }
            } else {
                return "redirect:/cave";
            }
        }
        return "redirect:/cave";

    }

    private void getBossForStartAttack(Long id, Model model) {
        if (id == 1) {
            model.addAttribute("boss", bossService.findBossById(1L).get());
            model.addAttribute("startAttack", 1);
        } else if (id == 2) {
            model.addAttribute("boss", bossService.findBossById(2L).get());
            model.addAttribute("startAttack", 1);
        } else if (id == 3) {
            model.addAttribute("boss", bossService.findBossById(3L).get());
            model.addAttribute("startAttack", 1);
        } else if (id == 4) {
            model.addAttribute("boss", bossService.findBossById(4L).get());
            model.addAttribute("startAttack", 1);
        } else if (id == 5) {
            model.addAttribute("boss", bossService.findBossById(5L).get());
            model.addAttribute("startAttack", 1);
        } else if (id == 6) {
            model.addAttribute("boss", bossService.findBossById(6L).get());
            model.addAttribute("startAttack", 1);
        } else if (id == 7) {
            model.addAttribute("boss", bossService.findBossById(7L).get());
            model.addAttribute("startAttack", 1);
        } else if (id == 8) {
            model.addAttribute("boss", bossService.findBossById(8L).get());
            model.addAttribute("startAttack", 1);
        } else if (id == 9) {
            model.addAttribute("boss", bossService.findBossById(9L).get());
            model.addAttribute("startAttack", 1);
        } else if (id == 10) {
            model.addAttribute("boss", bossService.findBossById(10L).get());
            model.addAttribute("startAttack", 1);
        }
    }

    private void bossHealth(Model model, Boss boss) {
        if (boss.getMaxHealth() == 5000) {
            model.addAttribute("bossExpBar", Math.floor((double) boss.getHealth() / 50));
        } else if (boss.getMaxHealth() == 10000) {
            model.addAttribute("bossExpBar", Math.floor((double) boss.getHealth() / 100));

        } else if (boss.getMaxHealth() == 25000) {
            model.addAttribute("bossExpBar", Math.floor((double) boss.getHealth() / 250));

        } else if (boss.getMaxHealth() == 50000) {
            model.addAttribute("bossExpBar", Math.floor((double) boss.getHealth() / 500));

        } else if (boss.getMaxHealth() == 100_000) {
            model.addAttribute("bossExpBar", Math.floor((double) boss.getHealth() / 1000));
        } else if (boss.getMaxHealth() == 300_000) {
            model.addAttribute("bossExpBar", Math.floor((double) boss.getHealth() / 3000));
        } else if (boss.getMaxHealth() == 500_000) {
            model.addAttribute("bossExpBar", Math.floor((double) boss.getHealth() / 5000));
        } else if (boss.getMaxHealth() == 1_000_000) {
            model.addAttribute("bossExpBar", Math.floor((double) boss.getHealth() / 10000));
        } else if (boss.getMaxHealth() == 3_000_000) {
            model.addAttribute("bossExpBar", Math.floor((double) boss.getHealth() / 30000));
        } else if (boss.getMaxHealth() == 10_000_000) {
            model.addAttribute("bossExpBar", Math.floor((double) boss.getHealth() / 100000));
        }
    }

    private Boss getBoss(long bossId, Boss boss) {
        if (bossId == 1) {
            boss = new Boss("Циклоп", 100L, 5000L, 5000L, 1L, "1",
                    new Date().getTime() + 60000);
        } else if (bossId == 2) {
            boss = new Boss("Кентавр", 300L, 10_000L, 10_000L, 1L, "2",
                    new Date().getTime() + 60000);
        } else if (bossId == 3) {
            boss = new Boss("Гаргона", 500L, 25_000L, 25_000L, 1L, "4",
                    new Date().getTime() + 60000);
        } else if (bossId == 4) {
            boss = new Boss("Минотавр", 1000L, 50_000L, 50_000L, 1L, "3",
                    new Date().getTime() + 60000);
        } else if (bossId == 5) {
            boss = new Boss("Амазонка", 1500L, 100_000L, 100_000L, 1L, "5",
                    new Date().getTime() + 60000);
        } else if (bossId == 6) {
            boss = new Boss("Цербер", 3000L, 300_000L, 300_000L, 1L, "6",
                    new Date().getTime() + 60000);
        } else if (bossId == 7) {
            boss = new Boss("Сирена", 3500L, 500_000L, 500_000L, 1L, "7",
                    new Date().getTime() + 60000);
        } else if (bossId == 8) {
            boss = new Boss("Анубис", 7000L, 1_000_000L, 1_000_000L, 1L, "8",
                    new Date().getTime() + 60000);
        } else if (bossId == 9) {
            boss = new Boss("Хаос", 7500L, 3_000_000L, 3_000_000L, 1L, "9",
                    new Date().getTime() + 60000);
        } else if (bossId == 10) {
            boss = new Boss("Аид", 15000L, 10_000_000L, 10_000_000L, 1L, "10",
                    new Date().getTime() + 60000);
        }
        return boss;
    }

    private void addExpAndSilverForUser(Boss boss, User us) {
        if (boss.getPower() == 100) {
            userService.updateExpAndSilverUserAtCave(10L, 15L, us.getId());
        } else if (boss.getPower() == 300) {
            userService.updateExpAndSilverUserAtCave(30L, 50L, us.getId());
        } else if (boss.getPower() == 500) {
            userService.updateExpAndSilverUserAtCave(60L, 150L, us.getId());
        } else if (boss.getPower() == 1000) {
            userService.updateExpAndSilverUserAtCave(180L, 500L, us.getId());
        } else if (boss.getPower() == 1500) {
            userService.updateExpAndSilverUserAtCave(360L, 1500L, us.getId());
        } else if (boss.getPower() == 3000) {
            userService.updateExpAndSilverUserAtCave(720L, 3000L, us.getId());
        } else if (boss.getPower() == 3500) {
            userService.updateExpAndSilverUserAtCave(1440L, 5000L, us.getId());
        } else if (boss.getPower() == 7000) {
            userService.updateExpAndSilverUserAtCave(3000L, 15000L, us.getId());
        } else if (boss.getPower() == 7500) {
            userService.updateExpAndSilverUserAtCave(6000L, 30000L, us.getId());
        } else if (boss.getPower() == 15000) {
            userService.updateExpAndSilverUserAtCave(10000L, 100000L, us.getId());
        }
    }

    private void adClanTrophies(Boss boss, Clan clan) {
        if (boss.getPower() == 100) {
            clanService.addCountB1forClan(clan.getId());
        } else if (boss.getPower() == 300) {
            clanService.addCountB2forClan(clan.getId());
        } else if (boss.getPower() == 500) {
            clanService.addCountB3forClan(clan.getId());
        } else if (boss.getPower() == 1000) {
            clanService.addCountB4forClan(clan.getId());
        } else if (boss.getPower() == 1500) {
            clanService.addCountB5forClan(clan.getId());
        } else if (boss.getPower() == 3000) {
            clanService.addCountB6forClan(clan.getId());
        } else if (boss.getPower() == 3500) {
            clanService.addCountB7forClan(clan.getId());
        } else if (boss.getPower() == 7000) {
            clanService.addCountB8forClan(clan.getId());
        } else if (boss.getPower() == 7500) {
            clanService.addCountB9forClan(clan.getId());
        } else if (boss.getPower() == 15000) {
            clanService.addCountB10forClan(clan.getId());
        }
    }
}
