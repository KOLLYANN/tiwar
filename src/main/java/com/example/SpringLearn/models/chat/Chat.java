package com.example.SpringLearn.models.chat;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Chat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String title;


    @OneToMany(mappedBy = "chat", cascade=CascadeType.ALL)
    List<Message> messages;

    public Chat(String title) {
        this.title = title;
    }
}
