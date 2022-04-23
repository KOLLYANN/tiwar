package com.example.tiwar.models.clan;

import com.example.tiwar.models.user.User;
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

    public static class Builder {

        private final String title;
        private final Long ownerId;
        private final List<User> users;

        public Builder(String title, Long ownerId, List<User> users) {
            this.title = title;
            this.ownerId = ownerId;
            this.users = users;
        }

        public Clan build() {
            return new Clan(this);
        }

    }

    private Clan(Builder builder) {
        title = builder.title;
        ownerId = builder.ownerId;
        users = builder.users;
        level = 1L;
        exp = 0L;
        expy = 360L;
        countB1 = 0L;
        countB2 = 0L;
        countB3 = 0L;
        countB4 = 0L;
        countB5 = 0L;
        countB6 = 0L;
        countB7 = 0L;
        countB8 = 0L;
        countB9 = 0L;
        countB10 = 0L;
        goldClan = 0L;
        silverClan = 0L;
    }

}
