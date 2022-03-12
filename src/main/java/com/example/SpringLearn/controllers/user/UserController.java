package com.example.SpringLearn.controllers.user;

import com.example.SpringLearn.models.user.User;
import com.example.SpringLearn.repositories.user.UserRepo;
import com.example.SpringLearn.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Objects;

@Controller
@RequestMapping("/profile")
public class UserController {

    private final UserService userService;
    final UserRepo userRepo;

    @Autowired
    public UserController(UserService userService, UserRepo userRepo) {
        this.userService = userService;
        this.userRepo = userRepo;
    }

    @GetMapping("{id}")
    public String user(
            @PathVariable("id") Long idd,
            Model model,
            @AuthenticationPrincipal User auth) {


        User use = userService.findUserById(idd);
        String active;
        String u_name;
        String path;
        long id;
        User us;
        if (!Objects.equals(use.getId(), auth.getId())) {
            us = userService.findUserById(use.getId());
            u_name = us.getUsername();
//                    userService.findUserById(use.getId()).getUsername();
            path = us.getPathToAvatar();
            id = us.getId();
        } else {
            us = userService.findUserById(auth.getId());
            u_name = us.getUsername();
//                    userService.findUserById(auth.getId()).getUsername();
            path = us.getPathToAvatar();
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

        model.addAttribute("auth", aut);
        model.addAttribute("exp", userService.expBar(aut));
        model.addAttribute("us_name", u_name);
        model.addAttribute("path", path);
        model.addAttribute("id_user", id);
        model.addAttribute("expPercent", Math.floor(((double) us.getExp() / us.getExpy()) * 100));
        model.addAttribute("us", us);

        return "users/usr";
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
//                    userService.findUserById(use.getId()).getUsername();
            path = us.getPathToAvatar();
            id = us.getId();
        } else {
            User us = userService.findUserById(auth.getId());
            u_name = us.getUsername();
//                    userService.findUserById(auth.getId()).getUsername();
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
