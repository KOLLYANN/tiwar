package com.example.tiwar.controllers.clan;

import com.example.tiwar.models.clan.Clan;
import com.example.tiwar.models.user.User;
import com.example.tiwar.services.clan.ClanService;
import com.example.tiwar.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/clan")
public class RequestClanController {

    final UserService userService;
    final ClanService clanService;

    @Autowired
    public RequestClanController(UserService userService, ClanService clanService) {
        this.userService = userService;
        this.clanService = clanService;
    }

    @GetMapping("/{id}/request")
    public String getRequestClan(
            @AuthenticationPrincipal User user,
            Model model
    ) {

        User userById = userService.findUserById(user.getId());

        List<User> usersRequest = clanService.findClanById(userById.getClan().getId()).get().getUsersRequest();

        model.addAttribute("us", userById);
        model.addAttribute("clan", userById.getClan());
        model.addAttribute("usersRequest", usersRequest);
        model.addAttribute("exp", userService.expBar(userById));

        return "clan/requestClan";
    }

    @PostMapping("/accept")
    public String acceptUserInClan(
            @AuthenticationPrincipal User user,
            @RequestParam("id") Long id,
            @RequestParam("userRequestId")Long userRequestId
    ) {
        Clan clanById = null;

        if(user.getClan().getOwnerId() == user.getId()) {
            clanById = clanService.findClanById(id).get();
//            User userById = userService.findUserById(user.getId());

            User userRequest = userService.findUserById(userRequestId);

            userRequest.setClan(clanById);
            clanService.saveClan(clanById);

            userRequest.setClanRequest(null);

            List<User> users = clanById.getUsers();
            for (User us : users) {
                if (us.getIdBossAttack() != null) {
                    userRequest.setIdBossAttack(us.getIdBossAttack());
                    userRequest.setBossDamage(0L);
                    userRequest.setClanRequest(null);
                    userService.saveUser(userRequest);
                } else {
                    userRequest.setIdBossAttack(null);
                    userRequest.setBossDamage(null);
                    userRequest.setClanRequest(null);
                    userService.saveUser(userRequest);
                }
            }

        }
        return "redirect:/clan/" + id + "/request";
    }


}

