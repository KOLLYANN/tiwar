package com.example.SpringLearn.repositories.clan;

import com.example.SpringLearn.models.clan.Clan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ClanRepo extends JpaRepository<Clan, Long> {

    @Transactional
    @Modifying
    @Query("update Clan c set c.level = c.level + 1, c.exp = 0, c.expy =:expy where c.id = :id")
    public void updateClanLevel(@Param("id") Long id, @Param("expy") Long expy);


}
