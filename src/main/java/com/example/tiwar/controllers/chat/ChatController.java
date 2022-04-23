package com.example.tiwar.controllers.chat;

import com.example.tiwar.models.chat.Chat;
import com.example.tiwar.models.chat.Message;
import com.example.tiwar.models.user.User;
import com.example.tiwar.repositories.chat.ChatRepository;
import com.example.tiwar.repositories.chat.MessageRepository;
import com.example.tiwar.repositories.chat.PagingMessage;
import com.example.tiwar.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/chat")
public class ChatController {

    final UserService userService;
    final ChatRepository chatRepository;
    final MessageRepository messageRepository;
    final PagingMessage pagingMessage;

    @Autowired
    public ChatController(UserService userService, ChatRepository chatRepository, MessageRepository messageRepository,
                          PagingMessage pagingMessage) {
        this.userService = userService;
        this.chatRepository = chatRepository;
        this.messageRepository = messageRepository;
        this.pagingMessage = pagingMessage;
    }

    static Pageable first = PageRequest.of(0, 10, Sort.by("id").descending());;

    @GetMapping
    public String getChat(
            @AuthenticationPrincipal User user,
            @RequestParam(value = "page", required = false, defaultValue = "0") String page,
            Model model
    ) {
        User userById = userService.findUserById(user.getId());


        Chat chat = chatRepository.findById(1L).get();

//        Pageable first = PageRequest.of(0, 3, Sort.by("id").descending());

        if(page.equals("previous")) {
            firstForClan = firstForClan.previousOrFirst();
        } else if(page.equals("next")) {
            firstForClan = firstForClan.next();
            int pageNumber = firstForClan.getPageNumber();
        }

        List<Message> allByChat = pagingMessage.findAllByChat(chat, firstForClan);


        model.addAttribute("messages", allByChat);
        model.addAttribute("us",userById);
        model.addAttribute("exp",userService.expBar(userById));

        return "chat/chat";
    }


    @PostMapping("/send")
    public String sendMessage(
            @AuthenticationPrincipal User user,
            @RequestParam("text") String text
    ) {

        if(text.length() >=2) {
            Chat chat = chatRepository.findById(1L).get();
            User userById = userService.findUserById(user.getId());

            Message message = new Message(text, userById, chat);
            messageRepository.save(message);
        }

        return "redirect:/chat";
    }

    static Pageable firstForClan = PageRequest.of(0, 10, Sort.by("id").descending());;

    @GetMapping("/clan")
    public String getChatClan(
            @AuthenticationPrincipal User user,
            @RequestParam(value = "page", required = false, defaultValue = "0") String page,
            Model model
    ) {
        User userById = userService.findUserById(user.getId());


        Chat chat = chatRepository.findByTitle("ClanChat"+ userById.getClan().getId());

//        Pageable first = PageRequest.of(0, 3, Sort.by("id").descending());

        if(page.equals("previous")) {
            firstForClan = firstForClan.previousOrFirst();
        } else if(page.equals("next")) {
            firstForClan = firstForClan.next();
        }

        List<Message> allByChat = pagingMessage.findAllByChat(chat, firstForClan);


        model.addAttribute("messages", allByChat);
        model.addAttribute("us",userById);
        model.addAttribute("exp",userService.expBar(userById));

        return "chat/chatClan";
    }

    @PostMapping("/clan/send")
    public String sendMessageClan(
            @AuthenticationPrincipal User user,
            @RequestParam("text") String text
    ) {


        if(text.length() >=2) {
            User userById = userService.findUserById(user.getId());

            Chat chat = chatRepository.findByTitle("ClanChat" + userById.getClan().getId());
            if(chat!=null) {
                Message message = new Message(text, userById, chat);
                messageRepository.save(message);
            }
        }

        return "redirect:/chat/clan";
    }




}
