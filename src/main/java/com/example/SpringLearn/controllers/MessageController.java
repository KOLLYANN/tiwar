package com.example.SpringLearn.controllers;

import com.example.SpringLearn.models.Message;
import com.example.SpringLearn.models.User;
import com.example.SpringLearn.services.MessageServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/message")
public class MessageController {

    final
    MessageServices messageServices;

    public MessageController(MessageServices messageServices) {
        this.messageServices = messageServices;
    }

    @GetMapping
    public String index(
            @AuthenticationPrincipal User user,
            Model model,
            RedirectAttributes red

        ) {
        List<Message> messageList = messageServices.messageList();
        model.addAttribute("messages", messageList);
        model.addAttribute("name", user.getUsername());
        int count = messageServices.messageList().size();
        model.addAttribute("count", count);
        return "message";
    }

    @PostMapping
    public String add(
            @AuthenticationPrincipal User user,
            @RequestParam String text,
            @RequestParam(required = false) String tag) {
        messageServices.addMessage(text, "смс", user);
        return "redirect:/message";
    }

    @PostMapping("/filter")
    public String filter(@RequestParam String filter,
                         Model model,
                         @AuthenticationPrincipal User user
                         ) {
        List<Message> messages;
        if (!filter.isEmpty()) {
            messages = messageServices.filterMessage(filter);
        } else {
            messages = messageServices.messageList();
        }
        int count = messageServices.messageList().size();
        model.addAttribute("messages", messages);
        model.addAttribute("filter", filter);
        model.addAttribute("name", user.getUsername());
        model.addAttribute("count", count);
        return "message";
    }


}
