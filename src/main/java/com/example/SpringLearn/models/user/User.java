package com.example.SpringLearn.models.user;

import com.example.SpringLearn.models.clan.Clan;
import com.example.SpringLearn.models.thing.Thing;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Table(name = "usr")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String username;
    String password;
    boolean active;
    String email;
    String pathToAvatar;
    String activateMailCode;
    @Column(name = "active_code")
    boolean activeCode;
    Long exp;
    Long expy;
    Long silver;
    Long userLevel;
    Long maxHealth;
    Long maxMana;


    Long skillMana;
    Long skillMaxMana;
    Long skillHealth;
    Long skillMaxHealth;
    Long skillPower;
    Long skillMaxPower;


    Long idBossAttack;
    Long bossDamage;


    Long health;
    Long mana;
    Long power;
    Long skill;
    Long userGold;
    Long pricePower;
    Long priceHealth;
    Long priceMana;
    Long expForClan;
    public boolean isAdmin() {
        return roles.contains(Role.ADMIN);
    }

    public Long getGold() {
        return userGold;
    }

    Long amountGoldForClan;
    Long amountSilverForClan;

    @ManyToOne
    Clan clan;

    @ManyToOne
    Clan clanRequest;

    @OneToMany(mappedBy = "user", cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    List<Thing> things;


    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "usr_role", joinColumns = @JoinColumn(name = "id_user"))
    @Enumerated(EnumType.STRING)
    Set<Role> roles;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public boolean isAccountNonExpired() {
        return active;
    }

    @Override
    public boolean isAccountNonLocked() {
        return active;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return active;
    }

    @Override
    public boolean isEnabled() {
        return isActive();
    }
}
