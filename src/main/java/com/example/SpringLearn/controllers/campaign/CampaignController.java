package com.example.SpringLearn.controllers.campaign;

import com.example.SpringLearn.models.user.User;
import com.example.SpringLearn.services.user.UserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.util.Date;

@Controller
@RequestMapping("/campaign")
public class CampaignController {

    final UserService userService;

    public CampaignController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping
    public String getCampaign(
            @AuthenticationPrincipal User user,
            Model model
    ) {

        User userById = userService.findUserById(user.getId());

        if (userById.getCampaign() != null && userById.getTimeCampaign() != null
                && userById.getTimeEndCampaign() != null) {

            long time = (userById.getTimeEndCampaign() - new Date().getTime()) / 1000;

            String strTime = String.valueOf(time);
            model.addAttribute("time", strTime);
        }
        model.addAttribute("us", userById);
        model.addAttribute("exp", userService.expBar(userById));

        return "campaign/campaign";
    }


    @PostMapping("/start")
    public String startCampaign(
            @AuthenticationPrincipal User user
    ) {

        User userById = userService.findUserById(user.getId());
        userService.updateCampaign(1L, userById);

        userService.timerTaskCampaign(userById);


        return "redirect:/campaign";
    }


}


