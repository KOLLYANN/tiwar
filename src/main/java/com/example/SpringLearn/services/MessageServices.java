package com.example.SpringLearn.services;

import com.example.SpringLearn.models.Message;
import com.example.SpringLearn.models.User;
import com.example.SpringLearn.repositories.MessageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MessageServices {

    final
    MessageRepo messageRepo;

    public MessageServices(MessageRepo messageRepo) {
        this.messageRepo = messageRepo;
    }

    public List<Message> messageList() {
        return messageRepo.findAll();
    }
    public void addMessage(String text, String tag, User user) {
        messageRepo.save(new Message(text, tag, user));
    }

    public List<Message> filterMessage(String filter) {
        List<Message> messages = messageRepo.findAll();
        return messages.stream().filter(message -> message.getTag().equals(filter)).collect(Collectors.toList());

    }


}
