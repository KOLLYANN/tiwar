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
    Long countB1;
    Long countB2;
    Long countB3;
    Long countB4;
    Long countB5;

    @OneToMany(mappedBy = "clan", fetch = FetchType.EAGER)
    List<User> users;


    public Clan(String title,Long countB1,Long countB2,Long countB3,Long countB4,Long countB5,
                Long exp,Long expy,Long level, List<User> users, Long ownerId) {
        this.title = title;
        this.countB1 = countB1;
        this.countB2 = countB2;
        this.countB3 = countB3;
        this.countB4 = countB4;
        this.countB5 = countB5;
        this.exp = exp;
        this.expy = expy;
        this.level = level;
        this.users = users;
        this.ownerId = ownerId;
    }
}
