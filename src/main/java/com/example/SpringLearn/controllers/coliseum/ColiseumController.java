package com.example.SpringLearn.controllers.coliseum;

import com.example.SpringLearn.models.user.DamageColiseum;
import com.example.SpringLearn.models.user.User;
import com.example.SpringLearn.services.user.DamageColiseumService;
import com.example.SpringLearn.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/coliseum")
public class ColiseumController {

    final UserService userService;
    final DamageColiseumService damageColiseumService;

    @Autowired
    public ColiseumController(UserService userService, DamageColiseumService damageColiseumService) {
        this.userService = userService;
        this.damageColiseumService = damageColiseumService;
    }

    @GetMapping
    public String getColiseum(
            @AuthenticationPrincipal User user,
            Model model
    ) {

        User userById = userService.findUserById(user.getId());


        User userAttack = null;
        if (userById.getIdAttackUserColiseum() != null && userById.getHealth() > 0) {
            userAttack = userService.findUserById(userById.getIdAttackUserColiseum());

            double exp = getHealthLine(userAttack);


            model.addAttribute("usrAttackHealth", exp);
            model.addAttribute("usrAttack", userAttack);
        }
        if (userById.getIdAttackUserColiseum() != null && userById.getHealth() > 0) {
            // auth
            List<DamageColiseum> damageColiseum = userById.getDamageColiseum();
            // Противник
            List<DamageColiseum> damageColiseumUs = userAttack.getDamageColiseum();

            List<DamageColiseum> attackList = new ArrayList<>();
            attackList.addAll(damageColiseum);
            attackList.addAll(damageColiseumUs);

            List<DamageColiseum> damageColiseums = attackList.stream()
                    .sorted((atk1, atk2) -> atk2.getId().compareTo(atk1.getId()))
                    .limit(15)
                    .toList();
            model.addAttribute("attackList", damageColiseums);
        }

        long countUsers = userService.findAll().stream().filter(u -> u.getStartAttackColiseum() != null).count();

        double expU = getHealthLine(userById);


        model.addAttribute("auth", userById);
        model.addAttribute("countUsers", countUsers);
        model.addAttribute("exp", userService.expBar(userById));
        model.addAttribute("usrHealth", expU);

        return "coliseum/coliseum";
    }

    private double getHealthLine(User userById) {
        Long maxHealthU = userById.getMaxHealth();
        Long healthU = userById.getHealth();

        double expU = Math.floor(100 / ((double) maxHealthU / healthU));

        if(expU > 100) {
            expU = 100;
        }


        return expU;
    }

    @PostMapping("/startatk")
    public String startAtk(
            @AuthenticationPrincipal User user,
            RedirectAttributes redirectAttributes
    ) {
        User userById = userService.findUserById(user.getId());

        if (userById.getHealth() > 0) {

            User userRandom = userService.getUserRandom(userById, userService);

            userById.setStartAttackColiseum(0L);
            if (userRandom != null) {
                userById.setIdAttackUserColiseum(userRandom.getId());
                userRandom.setIdAttackUserColiseum(userById.getId());
                userService.saveUser(userRandom);
            }

            userService.saveUser(userById);

            redirectAttributes.addFlashAttribute("usrAttack", userRandom);

            return "redirect:/coliseum";
        }


        return "redirect:/coliseum";
    }

    @PostMapping("/atk")
    public String atkUser(
            @AuthenticationPrincipal User user,
            RedirectAttributes redirectAttributes
    ) {
        User userById = userService.findUserById(user.getId());

        if (userById.getIdAttackUserColiseum() != null) {
            User usAttack = userService.findUserById(userById.getIdAttackUserColiseum());

            if (usAttack.getHealth() > 0) {
                userService.bossAttack(userById.getPower() / 100, usAttack.getId());

                DamageColiseum dm = new DamageColiseum((long) Math.floor((double) userById.getPower() / 100), userById);
                damageColiseumService.saveDamage(dm);


            } else {

                if(usAttack.getHealth() < 0) {
                    usAttack.setHealth(0L);
                }

                usAttack.setIdAttackUserColiseum(null);
                usAttack.setStartAttackColiseum(null);

                userService.updateAttackColiseum(null, userById.getId());

                userService.saveUser(usAttack);

                User usrRandom = userService.getUserRandom(userById, userService);

                userService.updateStartAttackColiseum(0L, userById.getId());

                if (usrRandom != null) {
                    userService.updateAttackColiseum(usrRandom.getId(), userById.getId());

                    usrRandom.setIdAttackUserColiseum(userById.getId());

                    userService.saveUser(usrRandom);
                }

                List<DamageColiseum> damageCol = damageColiseumService.findDamageAll();

                for (DamageColiseum damageColiseum : damageCol) {
                    if (damageColiseum.getUser().getId().equals(userById.getId())) {
                        damageColiseumService.deleteDamageById(damageColiseum);
                    }
                }
                userService.addSkull(userById.getId());
            }
        }
        return "redirect:/coliseum";
    }



    @PostMapping("/atkrnd")
    public String atkRandomUser(
            @AuthenticationPrincipal User user
    ) {
        User userById = userService.findUserById(user.getId());

        userById.setStartAttackColiseum(0L);


        return "redirect:/coliseum";
    }

    @PostMapping("/exit")
    public String exit(
            @AuthenticationPrincipal User user
    ) {
        User userById = userService.findUserById(user.getId());

        if (userById.getIdAttackUserColiseum() != null) {
            User usAttack = userService.findUserById(userById.getIdAttackUserColiseum());
            usAttack.setIdAttackUserColiseum(null);

            userById.setStartAttackColiseum(null);
            userById.setIdAttackUserColiseum(null);


            userService.saveUser(userById);
            userService.saveUser(usAttack);
        } else {
            userById.setStartAttackColiseum(null);
            userById.setIdAttackUserColiseum(null);

            userService.saveUser(userById);
        }


        return "redirect:/coliseum";
    }


}
