package com.example.SpringLearn.models;

import com.example.SpringLearn.models.user.Role;
import com.example.SpringLearn.models.user.User;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@NoArgsConstructor
@Data
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
