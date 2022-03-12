package com.example.SpringLearn.repositories.user;

import com.example.SpringLearn.models.user.User;
import com.example.SpringLearn.services.user.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/user")
@PreAuthorize("hasAuthority('ADMIN')")
public class AllUserController {

    final UserService userService;

    public AllUserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String user(Model model) {
        List<User> users = userService.findAll();
        model.addAttribute("users", users);
        return "/admin/allUser";
    }

    @GetMapping("{user}")
    public String userId(@PathVariable User user, Model model) {
        model.addAttribute("user", userService.findUserById(user.getId()));
        return "/users/editUser";
    }

    @PostMapping
    public String edit(RedirectAttributes red,
            @RequestParam("id") User user,
                       @RequestParam Long id,
                       @RequestParam String name) {

//        user.setUsername(name);
//        userService.saveUser(user);
        if(!userService.checkUsername(name)) {
            userService.updateUser(name, id);
        } else {
            System.out.println("Пользователь с таким именем есть");
            red.addFlashAttribute("nameExists", "Пользователь с таким именем есть");
        }
        return "redirect:/user/" + id;
    }




}
