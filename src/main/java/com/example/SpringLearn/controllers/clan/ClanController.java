package com.example.SpringLearn.controllers.clan;

import com.example.SpringLearn.models.clan.Clan;
import com.example.SpringLearn.models.user.User;
import com.example.SpringLearn.services.clan.ClanService;
import com.example.SpringLearn.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/clan")
public class ClanController {

    final UserService userService;
    final ClanService clanService;

    @Autowired
    public ClanController(UserService userService, ClanService clanService) {
        this.userService = userService;
        this.clanService = clanService;
    }


    @GetMapping
    public String getClan(
            Model model,
            @AuthenticationPrincipal User user
    ) {
        Optional<User> userByIdt = userService.findUserByIdt(user.getId());

        Clan clan = null;
        if(userByIdt.get().getClan() != null) {
            clan = userByIdt.get().getClan();
            List<User> clanUsers = clan.getUsers().stream()
                    .sorted((u1, u2) -> u2.getExpForClan().compareTo(u1.getExpForClan())).collect(Collectors.toList());
            model.addAttribute("clanGetUsers", clanUsers);
            clanService.updateLevelClan(clan.getId());
            model.addAttribute("expClan", clanService.expBarClan(clan));
            model.addAttribute("expPercentClan", Math.floor(((double) clan.getExp() / clan.getExpy()) * 100));

        }

        model.addAttribute("clan", clan);
        model.addAttribute("us", userByIdt.get());
        model.addAttribute("exp", userService.expBar(userByIdt.get()));



        return "clan/clan";

    }

    @GetMapping("/{id}")
    public String getClanById(
            @AuthenticationPrincipal User user,
            @PathVariable("id") Long id,
            Model model
        ) {
        Optional<Clan> clanById = clanService.findClanById(id);
        User auth = userService.findUserById(user.getId());
        if(auth.getClan() != null) {
            if (auth.getClan().equals(clanById.get())) {
                return "redirect:/clan";
            }
        }

        List<User> clanUsers = clanById.get().getUsers().stream()
                .sorted((u1, u2) -> u2.getExpForClan().compareTo(u1.getExpForClan())).collect(Collectors.toList());

        model.addAttribute("clan", clanById.get());
        model.addAttribute("clanGetUsers", clanUsers);
        model.addAttribute("user", userService.findUserById(user.getId()));
        model.addAttribute("us", userService.findUserById(user.getId()));
        model.addAttribute("expClan", clanService.expBarClan(clanById.get()));
        model.addAttribute("exp", userService.expBar(userService.findUserByIdt(user.getId()).get()));
        model.addAttribute("expPercentClan", Math.floor(((double) clanById.get().getExp() / clanById.get().getExpy()) * 100));



        return "clan/clanPage";
    }


    @PostMapping("/create")
    public String clanCreate(
            @AuthenticationPrincipal User authUser,
            @RequestParam("title") String title
    ) {
        Optional<User> userByIdt = userService.findUserByIdt(authUser.getId());
        if(userByIdt.get().getClan() == null) {
            Clan clan = new Clan(title, 0L,360L,1L ,List.of(authUser), userByIdt.get().getId());
            Clan clan1 = clanService.saveClan(clan);
            userByIdt.get().setClan(clan1);
            //add exp in clan
            userService.saveUser(userByIdt.get());
        }

        return "redirect:/clan";
    }

    @PostMapping("/subscribe")
    public String subscribe(
            @AuthenticationPrincipal User user,
            @RequestParam("id") Long id
    ) {
        Optional<Clan> clanById = clanService.findClanById(id);
        User userById = userService.findUserById(user.getId());
        userById.setClan(clanById.get());

        userService.saveUser(userById);

        return "redirect:/clan";
    }

    @PostMapping("/unsubscribe")
    public String unsubscribe(
            @AuthenticationPrincipal User user
    ) {
        User userById = userService.findUserById(user.getId());
        Long id = userById.getClan().getId();
        if(!Objects.equals(userById.getId(), userById.getClan().getOwnerId())) {
            userById.setClan(null);
            userById.setExpForClan(0L);
            userService.saveUser(userById);
        } else {
            System.out.println("Создатель");

            Clan clan = clanService.findClanById(id).get();
            List<User> userList = clan.getUsers();

            for (User us : userList) {
                us.setClan(null);
                us.setExpForClan(0L);
                userService.saveUser(us);
            }
            clanService.deleteClan(clan);

        }
        return "redirect:/clan";
    }


}
