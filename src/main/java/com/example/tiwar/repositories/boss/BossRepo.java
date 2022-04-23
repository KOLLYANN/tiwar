package com.example.tiwar.repositories.boss;

import com.example.tiwar.models.Boss;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface BossRepo extends JpaRepository<Boss, Long> {

    @Transactional
    @Modifying
    @Query("update Boss b set b.health = b.health - :minusHealth where b.id = :id")
    void bossOneMinusHealth(@Param("minusHealth") Long minusHealth, @Param("id") Long id);

}
