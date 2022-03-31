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
    void updateClanLevel(@Param("id") Long id, @Param("expy") Long expy);


    @Transactional
    @Modifying
    @Query("update Clan c set c.goldClan = c.goldClan + :addGoldClan where c.id = :id")
    void addGoldInClan(@Param("addGoldClan") Long addGoldClan, @Param("id") Long id);

    @Transactional
    @Modifying
    @Query("update Clan c set c.silverClan = c.silverClan + :addSilverClan where c.id = :id")
    void addSilverInClan(@Param("addSilverClan") Long addSilverClan, @Param("id") Long id);

    @Transactional
    @Modifying
    @Query("update Clan c set c.countB1 = c.countB1 + 1, c.exp = c.exp + 55 where c.id = :id")
    void addCountBossVictoryB1(@Param("id") Long id);

    @Transactional
    @Modifying
    @Query("update Clan c set c.countB2 = c.countB2 + 1, c.exp = c.exp + 155 where c.id = :id")
    void addCountBossVictoryB2(@Param("id") Long id);

    @Transactional
    @Modifying
    @Query("update Clan c set c.countB3 = c.countB3 + 1, c.exp = c.exp + 354 where c.id = :id")
    void addCountBossVictoryB3(@Param("id") Long id);

    @Transactional
    @Modifying
    @Query("update Clan c set c.countB4 = c.countB4 + 1, c.exp = c.exp + 851 where c.id = :id")
    void addCountBossVictoryB4(@Param("id") Long id);

    @Transactional
    @Modifying
    @Query("update Clan c set c.countB5 = c.countB5 + 1, c.exp = c.exp + 1541 where c.id = :id")
    void addCountBossVictoryB5(@Param("id") Long id);

    @Transactional
    @Modifying
    @Query("update Clan c set c.countB6 = c.countB6 + 1, c.exp = c.exp + 3210 where c.id = :id")
    void addCountBossVictoryB6(@Param("id") Long id);

    @Transactional
    @Modifying
    @Query("update Clan c set c.countB7 = c.countB7 + 1, c.exp = c.exp + 3501 where c.id = :id")
    void addCountBossVictoryB7(@Param("id") Long id);

    @Transactional
    @Modifying
    @Query("update Clan c set c.countB8 = c.countB8 + 1, c.exp = c.exp + 4501 where c.id = :id")
    void addCountBossVictoryB8(@Param("id") Long id);

    @Transactional
    @Modifying
    @Query("update Clan c set c.countB9 = c.countB9 + 1, c.exp = c.exp + 6501 where c.id = :id")
    void addCountBossVictoryB9(@Param("id") Long id);

    @Transactional
    @Modifying
    @Query("update Clan c set c.countB10 = c.countB10 + 1, c.exp = c.exp + 12014 where c.id = :id")
    void addCountBossVictoryB10(@Param("id") Long id);


}
