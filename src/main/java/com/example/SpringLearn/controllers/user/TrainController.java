package com.example.SpringLearn.controllers.user;

import com.example.SpringLearn.models.user.User;
import com.example.SpringLearn.services.user.UserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/train")
public class TrainController {

    final UserService userService;

    public TrainController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String getTrain(
            @AuthenticationPrincipal User user,
            Model model
    ) {
        User us = userService.findUserById(user.getId());

        model.addAttribute("us", us);
        model.addAttribute("exp", userService.expBar(us));
        return "users/trains";
    }

    @PostMapping("/addPower")
    public String addPower(
            @AuthenticationPrincipal User user,
            RedirectAttributes red
    ) {
        User us = userService.findUserById(user.getId());

        if(us.getSilver() > us.getPricePower() && us.getSkillPower() <= 100) {
            userService.plusUserPowerByTrain(us.getId());
        } else {
            System.out.println("Мало денег");
            red.addFlashAttribute("notSilver", 0);
        }


        return "redirect:/train";
    }
    @PostMapping("/addHealth")
    public String addHealth(
            @AuthenticationPrincipal User user,
            RedirectAttributes red
    ) {
        User us = userService.findUserById(user.getId());

        if(us.getSilver() > us.getPriceHealth() && us.getSkillHealth() <= 100) {
            userService.plusUserHealthByTrain(us.getId());
        } else {
            System.out.println("Мало денег");
            red.addFlashAttribute("notSilver", 0);
        }


        return "redirect:/train";
    }
    @PostMapping("/addMana")
    public String addMana(
            @AuthenticationPrincipal User user,
            RedirectAttributes red
    ) {
        User us = userService.findUserById(user.getId());

        if(us.getSilver() > us.getPriceMana() && us.getSkillMana() <= 100) {
            userService.plusUserManaByTrain(us.getId());
        } else {
            System.out.println("Мало денег");
            red.addFlashAttribute("notSilver", 0);
        }


        return "redirect:/train";
    }


}
