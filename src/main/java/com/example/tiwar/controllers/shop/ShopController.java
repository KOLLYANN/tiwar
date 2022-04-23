package com.example.tiwar.controllers.shop;

import com.example.tiwar.models.thing.Thing;
import com.example.tiwar.models.user.User;
import com.example.tiwar.services.thing.ThingService;
import com.example.tiwar.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/shop")
public class ShopController {

    final UserService userService;
    final ThingService thingService;

    @Autowired
    public ShopController(UserService userService, ThingService thingService) {
        this.userService = userService;
        this.thingService = thingService;
    }

    @GetMapping
    public String getShop(
            @AuthenticationPrincipal User user,
            Model model
    ) {
        User us = userService.findUserById(user.getId());

        model.addAttribute("us", us);
        model.addAttribute("exp", userService.expBar(us));

        return "shop/shop";
    }

    @GetMapping("/complect/{id}")
    public String getShopComplect1(
            @PathVariable("id") Long id,
            @AuthenticationPrincipal User user,
            Model model
    ) {
        User us = userService.findUserById(user.getId());

        List<Thing> allThings = thingService.findAll();
        List<Thing> complect = new ArrayList<>();
        for (Thing th : allThings) {
            if (th.getSkillGrade() == 10 && id == 1 && th.getUser() == null) {
                complect.add(th);
            } else if (th.getSkillGrade() == 50 && id == 2 && th.getUser() == null) {
                complect.add(th);
            } else if (th.getSkillGrade() == 100 && id == 3 && th.getUser() == null) {
                complect.add(th);
            } else if (th.getSkillGrade() == 200 && id == 4 && th.getUser() == null) {
                complect.add(th);
            } else if (th.getSkillGrade() == 300 && id == 5 && th.getUser() == null) {
                complect.add(th);
            } else if (th.getSkillGrade() == 303 && id == 6 && th.getUser() == null) {
                complect.add(th);
            }
            }
        model.addAttribute("complect1", complect);
        model.addAttribute("us", us);
        model.addAttribute("exp", userService.expBar(us));

        return "/shop/complect1";
    }


    @PostMapping("/buy/thing")
    public String buyThing(
            @AuthenticationPrincipal User user,
            @RequestParam("idThing") Long id,
            RedirectAttributes redirectAttributes
    ) {
        User userById = userService.findUserById(user.getId());
        List<Thing> thingsUser = userById.getThings();
        long count = thingsUser.stream().filter(thing -> thing.getState() == 0).count();
        List<Thing> things = thingService.findAll();
        for (Thing th : things) {
            if (th != null && th.getId() == id && userById.getUserGold() >= th.getPrice()
                    && count < 20
            ) {
                userService.buyThing(th.getPrice(), userById.getId());
                Thing thing = new Thing(th.getTitle(), th.getParameters(), th.getPathImage(),
                        th.getGrade(), th.getPosition(), th.getSkillGrade(), th.getPlace(), th.getState(),
                        th.getPrice(), th.getQuality(), th.getBorder(), 0L, th.getMiniGrade());
                thing.setUser(userById);
                thingService.saveThing(thing);
                redirectAttributes.addFlashAttribute("success", true);
            }
        }

        if (id > 21 && id <= 28) {
            return "redirect:/shop/complect/1";
        } else if (id > 7 && id <= 14) {
            return "redirect:/shop/complect/2";
        } else if (id > 14 && id <= 21) {
            return "redirect:/shop/complect/3";
        } else if (id >= 29 && id <= 35) {
            return "redirect:/shop/complect/4";
        } else if (id >= 36 && id <= 42) {
            return "redirect:/shop/complect/5";
        } else if (id >= 43 && id <= 49) {
            return "redirect:/shop/complect/6";
        }

        return "redirect:/shop/complect/1";
    }


}
