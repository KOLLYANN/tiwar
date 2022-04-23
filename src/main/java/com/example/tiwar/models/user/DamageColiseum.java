package com.example.tiwar.models.user;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class DamageColiseum {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    Long damage;

    @ManyToOne(cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    User user;

    public DamageColiseum(Long damage, User user) {
        this.damage = damage;
        this.user = user;
    }
}
