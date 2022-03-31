package com.example.SpringLearn.repositories.chat;

import com.example.SpringLearn.models.chat.Chat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatRepository extends JpaRepository<Chat, Long> {

    Chat findByTitle(String title);

}
