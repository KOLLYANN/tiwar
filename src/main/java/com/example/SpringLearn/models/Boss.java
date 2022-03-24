package com.example.SpringLearn.models;

import com.example.SpringLearn.models.user.Role;
import com.example.SpringLearn.models.user.User;
import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class Boss {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String name;
    Long power;
    Long health;
    Long maxHealth;
    Long attackExists;
    String titleAva;

    public Boss(String name, Long power, Long health, Long maxHealth, Long attackExists, String titleAva) {
        this.name = name;
        this.power = power;
        this.health = health;
        this.maxHealth = maxHealth;
        this.attackExists = attackExists;
        this.titleAva = titleAva;
    }
}
