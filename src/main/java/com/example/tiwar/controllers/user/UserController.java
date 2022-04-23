package com.example.tiwar.controllers.user;

import com.example.tiwar.models.thing.Thing;
import com.example.tiwar.models.user.User;
import com.example.tiwar.repositories.user.UserRepo;
import com.example.tiwar.services.thing.ThingService;
import com.example.tiwar.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/profile")
public class UserController {

    private final UserService userService;
    final UserRepo userRepo;
    final ThingService thingService;

    @Autowired
    public UserController(UserService userService, UserRepo userRepo, ThingService thingService) {
        this.userService = userService;
        this.userRepo = userRepo;
        this.thingService = thingService;
    }

    @GetMapping("/{id}")
    public String user(
            @PathVariable("id") Long idd,
            Model model,
            @AuthenticationPrincipal User auth) {


        User use = userService.findUserById(idd);

        String active;
        String u_name;
        long id;
        User us;
        if (!Objects.equals(use.getId(), auth.getId())) {
            us = userService.findUserById(use.getId());
            u_name = us.getUsername();
            id = us.getId();
        } else {
            us = userService.findUserById(auth.getId());
            u_name = us.getUsername();
            id = us.getId();
            if (us.isActiveCode()) {
                active = "Активирован";
            } else {
                active = "Не активирован";
            }
            model.addAttribute("code", active);
        }


        User aut = userService.findUserById(auth.getId());
        userService.updateLevelUser(aut);

        List<Thing> things = us.getThings();
        long count = things.stream().filter(thing -> thing.getState() == 0).count();
        long countSmith = things.stream().filter(th -> th.getState() == 1L).mapToLong(Thing::getSmith).sum();
        List<Thing> thingsUserEquip = things.stream()
                .filter(thing -> thing.getState().equals(1L))
                .sorted(Comparator.comparing(Thing::getPosition))
                .toList();

        long amountThing = thingsUserEquip.size();

        model.addAttribute("auth", aut);
        model.addAttribute("exp", userService.expBar(aut));
        model.addAttribute("us_name", u_name);
        model.addAttribute("id_user", id);
        model.addAttribute("expPercent", Math.floor(((double) us.getExp() / us.getExpy()) * 100));
        model.addAttribute("us", us);
        model.addAttribute("thingsUserEquip", thingsUserEquip);
        model.addAttribute("amountThing", amountThing);
        model.addAttribute("count", count);
        model.addAttribute("countSmith", countSmith);

        return "users/usr";
    }


    @GetMapping("/{id}/equip")
    public String getEquip(@PathVariable("id") Long id,
                           Model model,
                           @AuthenticationPrincipal User auth) {

        User userById = userService.findUserById(id);

        User authUser = userService.findUserById(auth.getId());
        List<Thing> things = userById.getThings();

        model.addAttribute("things", things);
        model.addAttribute("us", userById);
        model.addAttribute("auth", authUser);
        model.addAttribute("exp", userService.expBar(authUser));

        return "users/equip";
    }

    @GetMapping("/smith")
    public String getSmith(Model model,
                           @AuthenticationPrincipal User auth) {

        User userById = userService.findUserById(auth.getId());

        List<Thing> things = userById.getThings().stream()
                .filter(th -> th.getState().equals(1L))
                .sorted((th1, th2) -> th2.getPosition().compareTo(th1.getPosition()))
                .toList();

        model.addAttribute("things", things);
        model.addAttribute("us", userById);
        model.addAttribute("exp", userService.expBar(userById));

        return "users/smith";
    }

    @PostMapping("/smith/up")
    public String upThing(Model model,
                          @AuthenticationPrincipal User auth,
                          @RequestParam("thingId") Long thingId) {

        User userById = userService.findUserById(auth.getId());
        Thing thing = thingService.findById(thingId);
        if (userById.getUserGold() >= 50 && thing.getSmith() < 90) {

            thingService.upThing(thing.getId());
            thingService.saveThing(thing);

            userService.buyThing(50L, userById.getId());
            userService.plusUserParametersThing(50L, userById.getId());
            userService.saveUser(userById);
        }

        return "redirect:/profile/smith";
    }

    @GetMapping("/{id}/bag")
    public String getBag(@PathVariable("id") Long id,
                         Model model,
                         @AuthenticationPrincipal User auth) {

        if (id == auth.getId()) {
            User userById = userService.findUserById(auth.getId());

            List<Thing> thingsUser1 = userById.getThings().stream().
                    filter(th -> th.getState() == 1).toList();

            List<Thing> thingsUser2 = userById.getThings().stream().
                    filter(th -> th.getState() == 0).toList();


            model.addAttribute("things", thingsUser2);
            model.addAttribute("things1", thingsUser1);
            model.addAttribute("us", userById);
            model.addAttribute("exp", userService.expBar(userById));
        } else {
            return "redirect:/";
        }

        return "users/bag";
    }

    @PostMapping("/bag/putOn")
    public String putOnThing(
            @AuthenticationPrincipal User user,
            @RequestParam("thingId") Long thingId,
            RedirectAttributes red
    ) {
        User userById = userService.findUserById(user.getId());
        List<Thing> thingsUser = userById.getThings();

        Thing thing = thingService.findById(thingId);

        for (int i = 0; i < thingsUser.size(); i++) {
            Thing thingUser = thingsUser.get(i);
            if (!thingUser.equals(thing)) {
                if (thingUser.getPosition().equals(thing.getPosition())) {
                    if (thingUser.getState().equals(1L)) {
                        if (thingUser.getParameters() <= thing.getParameters()) {
                            thingUser.setState(0L);
                            thing.setState(1L);
                            userService.minusUserParametersThing(thingUser.getParameters(), userById.getId());
                            userService.plusUserParametersThing(thing.getParameters(), userById.getId());
                            thingService.saveAllThing(List.of(thingUser, thing));
                        }
                    }
                }
            }
        }

        return "redirect:/profile/" + user.getId() + "/bag";
    }


    @PostMapping("/bag/sell")
    public String sellThing(
            @AuthenticationPrincipal User user,
            @RequestParam("thingId") Long thingId) {

        User userById = userService.findUserById(user.getId());
        List<Thing> things = thingService.findAll();
        Iterator<Thing> thingIterator = things.listIterator();

        while (thingIterator.hasNext()) {
            Thing th = thingIterator.next();
            if (Objects.equals(th.getId(), thingId)) {
                userService.sellThing(th.getPrice() / 3, userById.getId());
                thingService.deleteThingById(th);
                break;
            }
        }

        return "redirect:/profile/" + userById.getId() + "/bag";
    }

    @GetMapping("/{id}/config")
    public String userConfig(
            @PathVariable("id") Long idd,
            Model model,
            @AuthenticationPrincipal User auth) {

        User use = userService.findUserById(idd);
        String active;
        String u_name;
        String path;
        long id;
        if (auth == null || use.getId() != auth.getId()) {
            User us = userService.findUserById(use.getId());
            u_name = us.getUsername();
            path = us.getPathToAvatar();
            id = us.getId();
        } else {
            User us = userService.findUserById(auth.getId());
            u_name = us.getUsername();
            path = us.getPathToAvatar();
            id = us.getId();
            if (us.isActiveCode()) {
                active = "Активирован";
            } else {
                active = "Не активирован";
            }
            model.addAttribute("code", active);
        }

        model.addAttribute("us_name", u_name);
        model.addAttribute("path", path);
        model.addAttribute("id_user", id);

        return "users/configUser";
    }


    @PostMapping("/{user}/upload")
    public String uploadAvatar(
            @AuthenticationPrincipal User user,
            @RequestParam("file") MultipartFile multipartFile,
            RedirectAttributes redirectAttributes
    ) {
        if (!multipartFile.isEmpty()) {
            userService.uploadAvatar(multipartFile, user);
        } else {
            redirectAttributes.addFlashAttribute("avaNotExists", "Выберите файл");
        }

        return "redirect:/profile/" + user.getId();
    }

    @PostMapping("/{user}/edit")
    public String edit(
            @RequestParam("id") User user,
            @RequestParam String name,
            @RequestParam String email
    ) {
        if (!email.equals("")) {
            if (userService.updateUsrEmail(user, email)) {
                userRepo.save(user);
            }
        }
        if (!name.equals("")) {
            if (userService.updateUsrName(user, name)) {
                userRepo.save(user);
            }
        }

        return "redirect:/profile/" + user.getId();
    }

    @PostMapping("/deleteAva")
    public String deleteAva(@AuthenticationPrincipal User user) {
        userService.fileDelete(user);
        return "redirect:/profile/" + user.getId();

    }

}
