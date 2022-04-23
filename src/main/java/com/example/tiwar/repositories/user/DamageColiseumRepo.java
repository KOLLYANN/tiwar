package com.example.tiwar.repositories.user;

import com.example.tiwar.models.user.DamageColiseum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DamageColiseumRepo extends JpaRepository<DamageColiseum, Long> {
}
