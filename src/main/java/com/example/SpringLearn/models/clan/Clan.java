package com.example.SpringLearn.models.clan;

import com.example.SpringLearn.models.user.User;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
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
    Long countB1;
    Long countB2;
    Long countB3;
    Long countB4;
    Long countB5;
    Long countB6;
    Long countB7;
    Long countB8;
    Long countB9;
    Long countB10;
    Long goldClan;
    Long silverClan;

    @OneToMany(mappedBy = "clanRequest")
    List<User> usersRequest;

    @OneToMany(mappedBy = "clan")
    List<User> users;


    public Clan(String title,Long goldClan,Long silverClan,Long countB1,Long countB2,Long countB3,Long countB4,Long countB5,
                Long countB6,Long countB7,Long countB8,Long countB9,Long countB10,
                Long exp,Long expy,Long level, List<User> users, Long ownerId) {
        this.title = title;
        this.goldClan = goldClan;
        this.silverClan = silverClan;
        this.countB1 = countB1;
        this.countB2 = countB2;
        this.countB3 = countB3;
        this.countB4 = countB4;
        this.countB5 = countB5;
        this.countB6 = countB6;
        this.countB7 = countB7;
        this.countB8 = countB8;
        this.countB9 = countB9;
        this.countB10 = countB10;
        this.exp = exp;
        this.expy = expy;
        this.level = level;
        this.users = users;
        this.ownerId = ownerId;
    }
}
