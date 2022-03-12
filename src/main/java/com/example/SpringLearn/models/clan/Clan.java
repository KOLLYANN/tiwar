package com.example.SpringLearn.models.clan;

import com.example.SpringLearn.models.user.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "clans")
public class Clan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String title;
    Long exp;
    Long expy;
    Long ownerId;
    Long level;

    @OneToMany(mappedBy = "clan", fetch = FetchType.EAGER)
    List<User> users;


    public Clan(String title, Long exp,Long expy,Long level, List<User> users, Long ownerId) {
        this.title = title;
        this.exp = exp;
        this.expy = expy;
        this.level = level;
        this.users = users;
        this.ownerId = ownerId;
    }
}
