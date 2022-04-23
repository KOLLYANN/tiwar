package com.example.tiwar.models;

import lombok.*;

import javax.persistence.*;

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
    Long time;

    public Boss(String name, Long power, Long health, Long maxHealth, Long attackExists, String titleAva, Long time) {
        this.name = name;
        this.power = power;
        this.health = health;
        this.maxHealth = maxHealth;
        this.attackExists = attackExists;
        this.titleAva = titleAva;
        this.time = time;
    }
}
