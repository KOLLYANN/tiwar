package com.example.SpringLearn.repositories.chat;

import com.example.SpringLearn.models.chat.Chat;
import com.example.SpringLearn.models.chat.Message;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PagingMessage extends PagingAndSortingRepository<Message, Long> {

    List<Message> findAllByChat(Chat chat, Pageable pageable);


}
