package com.example.SpringLearn.controllers.user;

import com.example.SpringLearn.models.thing.Thing;
import com.example.SpringLearn.models.user.User;
import com.example.SpringLearn.repositories.user.UserRepo;
import com.example.SpringLearn.services.thing.ThingService;
import com.example.SpringLearn.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Arrays;
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

        Thing helmet = null;
        Thing shoulder = null;
        Thing shield = null;
        Thing bracers = null;
        Thing greaves = null;
        Thing armor = null;
        Thing sword = null;
        Thing ring1 = null;
        Thing ring2 = null;
        int amountThing = 0;
        for (Thing th : things) {
            if (th != null && th.getState() == 1) {
                amountThing++;
                System.out.println(th.getPlace());
                switch (th.getPlace()) {
                    case "Голова" -> helmet = th;
                    case "Плечо" -> shoulder = th;
                    case "Щит" -> shield = th;
                    case "Наручи" -> bracers = th;
                    case "Наголенники" -> greaves = th;
                    case "Броня" -> armor = th;
                    case "Меч" -> sword = th;
                    case "Кольцо1" -> ring1 = th;
                    case "Кольцо2" -> ring2 = th;
                }
            }
        }
        model.addAttribute("auth", aut);
        model.addAttribute("exp", userService.expBar(aut));
        model.addAttribute("us_name", u_name);
        model.addAttribute("id_user", id);
        model.addAttribute("expPercent", Math.floor(((double) us.getExp() / us.getExpy()) * 100));
        model.addAttribute("us", us);
        model.addAttribute("helmet", helmet);
        model.addAttribute("shoulder", shoulder);
        model.addAttribute("shield", shield);
        model.addAttribute("bracers", bracers);
        model.addAttribute("greaves", greaves);
        model.addAttribute("sword", sword);
        model.addAttribute("armor", armor);
        model.addAttribute("ring1", ring1);
        model.addAttribute("ring2", ring2);
        model.addAttribute("amountThing", amountThing);

        return "users/usr";
    }


    @GetMapping("/{id}/equip")
    public String getEquip(@PathVariable("id") Long id,
                           Model model,
                           @AuthenticationPrincipal User auth) {

        User userById = userService.findUserById(id);

        List<Thing> things = userById.getThings();

        model.addAttribute("things", things);
        model.addAttribute("us", userById);
        model.addAttribute("exp", userService.expBar(userById));

        return "users/equip";
    }

    @GetMapping("/{id}/bag")
    public String getBag(@PathVariable("id") Long id,
                         Model model,
                         @AuthenticationPrincipal User auth) {

        if (id == auth.getId()) {
            User userById = userService.findUserById(auth.getId());

            List<Thing> things = userById.getThings();

            model.addAttribute("things", things);
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
        List<Thing> things = userById.getThings();

        Thing thing = thingService.findById(thingId);

        for (int i = 0; i < things.size(); i++) {
//                    if(things.get(i).getSkillGrade() > thing.getSkillGrade()) {
                        if (thing.getPosition() == 1 && things.get(i).getPosition() == 1) {
                            if (things.get(i).getState().equals(0L)) {
                                thing.setState(1L);
                                thingService.saveThing(thing);
                                userService.plusUserParametersThing(thing.getParameters(), userById.getId());

                            } else {
                                userService.minusUserParametersThing(things.get(i).getParameters(), userById.getId());
                                thingService.deleteThingById(things.get(i));
                            }
                        } else if (thing.getPosition() == 2 && things.get(i).getPosition() == 2) {
                            if (things.get(i).getState().equals(0L)) {
                                thing.setState(1L);
                                thingService.saveThing(thing);
                                userService.plusUserParametersThing(thing.getParameters(), userById.getId());
                            } else {
                                userService.minusUserParametersThing(things.get(i).getParameters(), userById.getId());
                                thingService.deleteThingById(things.get(i));
                            }
                        } else if (thing.getPosition() == 3 && things.get(i).getPosition() == 3) {
                            if (things.get(i).getState().equals(0L)) {
                                thing.setState(1L);
                                thingService.saveThing(thing);
                                userService.plusUserParametersThing(thing.getParameters(), userById.getId());
                            } else {
                                userService.minusUserParametersThing(things.get(i).getParameters(), userById.getId());
                                thingService.deleteThingById(things.get(i));
                            }
                        } else if (thing.getPosition() == 4 && things.get(i).getPosition() == 4) {
                            if (things.get(i).getState().equals(0L)) {
                                thing.setState(1L);
                                thingService.saveThing(thing);
                                userService.plusUserParametersThing(thing.getParameters(), userById.getId());
                            } else {
                                userService.minusUserParametersThing(things.get(i).getParameters(), userById.getId());
                                thingService.deleteThingById(things.get(i));
                            }
                        } else if (thing.getPosition() == 5 && things.get(i).getPosition() == 5) {
                            if (things.get(i).getState().equals(0L)) {
                                thing.setState(1L);
                                thingService.saveThing(thing);
                                userService.plusUserParametersThing(thing.getParameters(), userById.getId());
                            } else {
                                userService.minusUserParametersThing(things.get(i).getParameters(), userById.getId());
                                thingService.deleteThingById(things.get(i));
                            }
                        } else if (thing.getPosition() == 6 && things.get(i).getPosition() == 6) {
                            if (things.get(i).getState().equals(0L)) {
                                thing.setState(1L);
                                thingService.saveThing(thing);
                                userService.plusUserParametersThing(thing.getParameters(), userById.getId());
                            } else {
                                userService.minusUserParametersThing(things.get(i).getParameters(), userById.getId());
                                thingService.deleteThingById(things.get(i));
                            }
                        } else if (thing.getPosition() == 7 && things.get(i).getPosition() == 7) {
                            if (things.get(i).getState().equals(0L)) {
                                thing.setState(1L);
                                thingService.saveThing(thing);
                                userService.plusUserParametersThing(thing.getParameters(), userById.getId());
                            } else {
                                userService.minusUserParametersThing(things.get(i).getParameters(), userById.getId());
                                thingService.deleteThingById(things.get(i));
                            }
                        }
                    }

            return "redirect:/profile/" + user.getId() + "/bag";
        }

//    @PostMapping("/bag/putOff")
//    public String putOffThing(
//            @AuthenticationPrincipal User user,
//            @RequestParam("thingId") Long thingId
//    ) {
//
//        User userById = userService.findUserById(user.getId());
//        List<Thing> things = thingService.findAll();
//        Iterator<Thing> thingIterator = things.listIterator();
//        while (thingIterator.hasNext()) {
//            Thing th = thingIterator.next();
//            if (th != null) {
//                if (th.getId() == thingId) {
//                    th.setState(0L);
//                    thingService.saveThing(th);
//                }
//            }
//        }
//
//        return "redirect:/profile/" + user.getId() + "/equip";
//    }

        @PostMapping("/bag/sell")
        public String sellThing (
                @AuthenticationPrincipal User user,
                @RequestParam("thingId") Long thingId){

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
        public String userConfig (
                @PathVariable("id") Long idd,
                Model model,
                @AuthenticationPrincipal User auth){

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
        public String uploadAvatar (
                @AuthenticationPrincipal User user,
                @RequestParam("file") MultipartFile multipartFile,
                RedirectAttributes redirectAttributes
    ){
            if (!multipartFile.isEmpty()) {
                userService.uploadAvatar(multipartFile, user);
            } else {
                redirectAttributes.addFlashAttribute("avaNotExists", "Выберите файл");
            }

            return "redirect:/profile/" + user.getId();
        }

        @PostMapping("/{user}/edit")
        public String edit (
                @RequestParam("id") User user,
                @RequestParam String name,
                @RequestParam String email
    ){
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
        public String deleteAva (@AuthenticationPrincipal User user){
            userService.fileDelete(user);
            return "redirect:/profile/" + user.getId();

        }

    }
