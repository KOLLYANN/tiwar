package com.example.SpringLearn.controllers.shop;

import com.example.SpringLearn.models.thing.Thing;
import com.example.SpringLearn.models.user.User;
import com.example.SpringLearn.services.thing.ThingService;
import com.example.SpringLearn.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
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
        List<Thing> complect1 = new ArrayList<>();
        List<Thing> complect2 = new ArrayList<>();
        for (Thing th : allThings) {
            if(th.getSkillGrade() <=10 && th.getUser() == null) {
                complect1.add(th);
            } else if(th.getSkillGrade() <= 50 && th.getUser() == null) {
                complect2.add(th);
            }
        }

        model.addAttribute("complect1", complect1);
        model.addAttribute("complect2", complect2);
        model.addAttribute("us", us);
        model.addAttribute("exp", userService.expBar(us));
        if(id == 1) {
            return "/shop/complect1";
        } else if(id == 2) {
            return "/shop/complect2";
        } else {
            return "redirect:/";
        }
    }


    @PostMapping("/buy/thing")
    public String buyThing(
        @AuthenticationPrincipal User user,
        @RequestParam("idThing") Long id,
        RedirectAttributes redirectAttributes
    ) {
        User userById = userService.findUserById(user.getId());
        List<Thing> things = thingService.findAll();
        for(Thing th : things) {
            if(th != null && th.getId() == id && userById.getUserGold() >= th.getPrice()) {
                userService.buyThing(th.getPrice(), userById.getId());
                Thing thing = new Thing(th.getTitle(), th.getParameters(), th.getPathImage(),
                        th.getGrade(), th.getPosition(), th.getSkillGrade(), th.getPlace(),th.getState(),th.getPrice());
                thing.setUser(userById);
                thingService.saveThing(thing);
                redirectAttributes.addFlashAttribute("success",true);
            }
        }

        if(id <= 7) {
            return "redirect:/shop/complect/1";
        } else if(id > 7 && id <= 50) {
            return "redirect:/shop/complect/2";
        }

        return "redirect:/shop/complect/1";
    }




}
