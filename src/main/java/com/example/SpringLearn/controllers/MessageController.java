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
            Model model) {
        List<Message> messageList = messageServices.messageList();
        model.addAttribute("messages", messageList);
        model.addAttribute("name", user.getUsername());
        return "message";
    }

    @PostMapping
    public String add(
            @AuthenticationPrincipal User user,
            @RequestParam String text,
            @RequestParam String tag) {
        messageServices.addMessage(text, tag, user);
        return "redirect:/message";
    }

    @PostMapping("/filter")
    public String filter(@RequestParam String filter, Model model, RedirectAttributes redirectAttributes) {
        List<Message> messages;
        if (!filter.isEmpty()) {
            messages = messageServices.filterMessage(filter);
        } else {
            messages = messageServices.messageList();
        }
        model.addAttribute("messages", messages);
        return "message";
    }


}
