package com.example.SpringLearn.repositories.user;

import com.example.SpringLearn.models.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {

    @Modifying
    @Transactional
    @Query("update User u set u.pathToAvatar = :path where u.id = :id")
    void updateAvatar(@Param("path") String path, @Param("id") Long id);


    @Modifying
    @Transactional
    @Query("update User u set u.username = :name where u.id = :id")
    void updateName(@Param("name") String username, @Param("id") long id);

    @Modifying
    @Transactional
    @Query("update User u set u.idBossAttack = null, u.bossDamage = null where u.id = :id")
    void updateToNullBossAttack(@Param("id") long id);

    @Modifying
    @Transactional
    @Query("update User u set u.idBossAttack = :idBoss where u.id = :id")
    void updateBossAttack(@Param("idBoss")Long idBoss,@Param("id") long id);

    @Modifying
    @Transactional
    @Query("update User u set u.bossDamage = :bossDamage where u.id = :id")
    void updateBossDamage(@Param("bossDamage")Long bossDamage,@Param("id") long id);

    @Modifying
    @Transactional
    @Query("update User u set u.bossDamage = u.bossDamage + :bossDamage where u.id = :id")
    void bossDamage(@Param("bossDamage")Long v,@Param("id") long id);

    @Modifying
    @Transactional
    @Query("update User u set u.skull = u.skull + 1 where u.id = :id")
    void addSkull(@Param("id") long id);

    @Modifying
    @Transactional
    @Query("update User u set u.idAttackUserColiseum = :idUserAttack where u.id = :id")
    void updateAttackColiseum(@Param("idUserAttack") Long idUserAttack,@Param("id") long id);

    @Modifying
    @Transactional
    @Query("update User u set u.startAttackColiseum = :flag where u.id = :id")
    void updateStartAttackColiseum(@Param("flag") Long flag,@Param("id") long id);


    @Modifying
    @Transactional
    @Query("update User u set u.exp = u.exp + :exp,u.expForClan = u.expForClan + :expForClan, u.silver = u.silver + :silver where u.id = :id")
    void updateExpUser(@Param("exp") long exp,@Param("expForClan") Long expForClan,@Param("silver") long silver ,@Param("id") long id);

    @Modifying
    @Transactional
    @Query("update User u set u.exp = u.exp + :exp,u.silver = u.silver + :silver where u.id = :id")
    void updateExpAndSilverUserAtCave(@Param("exp") long exp,@Param("silver") long silver ,@Param("id") long id);


    @Modifying
    @Transactional
    @Query("update User u set u.mana = u.mana - 41, u.health = u.health - 12 where u.id = :id")
    void minusManaUserVictory(@Param("id") long id);

    @Modifying
    @Transactional
    @Query("update User u set u.health = u.health - :bossAttack where u.id = :id")
    void bossAttack(@Param("bossAttack") Long bossAttack, @Param("id") long id);


    @Modifying
    @Transactional
    @Query("update User u set u.mana = u.mana - 50, u.health = u.health - 15 where u.id = :id")
    void minusManaUserDefeat(@Param("id") long id);

    @Modifying
    @Transactional
    @Query("update User u set u.mana = u.mana + 13 where u.id = :id")
    void plusManaUser(@Param("id") Long id);

    @Modifying
    @Transactional
    @Query("update User u set u.userGold = u.userGold + :priceThing where u.id = :id")
    void sellThing(@Param("priceThing")Long priceThing, @Param("id") Long id);

    @Modifying
    @Transactional
    @Query("update User u set u.userGold = u.userGold - :priceThing where u.id = :id")
    void buyThing(@Param("priceThing")Long priceThing, @Param("id") Long id);


    @Modifying
    @Transactional
    @Query("update User u set u.health = u.health + 28 where u.id = :id")
    void plusUserHealth(@Param("id") Long id);

    @Modifying
    @Transactional
    @Query("update User u set u.power = u.power + 10, u.skill = u.skill + 1," +
            " u.pricePower = u.pricePower + 500,u.skillPower = u.skillPower + 1, u.silver = u.silver - u.pricePower where u.id = :id")
    void plusUserPowerByTrain(@Param("id") Long id);


    @Modifying
    @Transactional
    @Query("update User u set u.power = u.power + :parameter , u.maxHealth = u.maxHealth + :parameter," +
            " u.maxMana = u.maxMana + :parameter where u.id = :id")
    void plusUserParametersThing(@Param("parameter") Long parameters, @Param("id") Long id);

    @Modifying
    @Transactional
    @Query("update User u set u.power = u.power - :parameter , u.maxHealth = u.maxHealth - :parameter," +
            " u.maxMana = u.maxMana - :parameter where u.id = :id")
    void minusUserParametersThing(@Param("parameter") Long parameters, @Param("id") Long id);

    @Modifying
    @Transactional
    @Query("update User u set u.maxHealth = u.maxHealth + 50, u.skill = u.skill + 1," +
            "  u.priceHealth = u.priceHealth + 500,u.skillHealth = u.skillHealth + 1, u.silver = u.silver - u.priceHealth where u.id = :id")
    void plusUserHealthByTrain(@Param("id") Long id);

    @Modifying
    @Transactional
    @Query("update User u set u.maxMana = u.maxMana + 50, u.skill = u.skill + 1," +
            "  u.priceMana = u.priceMana + 500,u.skillMana = u.skillMana + 1, u.silver = u.silver - u.priceMana where u.id = :id")
    void plusUserManaByTrain(@Param("id") Long id);

    @Modifying
    @Transactional
    @Query("update Clan c set c.exp = c.exp + :expAddForClan where c.id = :id")
    void updateExpClan(@Param("id") Long id, @Param("expAddForClan") Long expAddForClan);

    @Modifying
    @Transactional
    @Query("update User u set u.pathToAvatar = 'ava.png' where u.id = :id")
    void deleteAvatar(@Param("id") Long id);

    @Modifying
    @Transactional
    @Query("update User u set u.campaign = :flag where u.id = :id")
    void updateCampaign(@Param("flag") Long flag, @Param("id") Long id);

    @Query(value = "select campaign from usr where id = ?1", nativeQuery = true)
    Long getCampaign(Long id);

    @Transactional
    @Modifying
    @Query("update User u set u.timeCampaign = :time where u.id = :id")
    void setTimeStartCampaign(@Param("time") long time,@Param("id") Long id);

    @Transactional
    @Modifying
    @Query("update User u set u.timeEndCampaign = :time where u.id = :id")
    void setTimeEndCampaign(@Param("time") long time,@Param("id") Long id);





    @Transactional
    @Modifying
    @Query("update User c set c.userLevel = c.userLevel + 1, c.userGold = c.userGold + (c.userLevel * 3), c.exp = 0," +
            " c.expy = :expy where c.id = :id")
    void updateUserLevel(@Param("id") Long id, @Param("expy") Long expy);

    @Transactional
    @Modifying
    @Query("update User u set u.userGold = u.userGold - 2500 where u.id = :id")
    void minusGoldForClanCreate(@Param("id") Long id);

    @Transactional
    @Modifying
    @Query("update User u set u.userGold = u.userGold - :addGoldClan," +
            " u.amountGoldForClan = u.amountGoldForClan + :addGoldClan where u.id = :id")
    void addGoldForClan(@Param("addGoldClan") Long addGoldClan, @Param("id") Long id);

    @Transactional
    @Modifying
    @Query("update User u set u.silver = u.silver - :addSilverClan," +
            " u.amountSilverForClan = u.amountSilverForClan + :addSilverClan where u.id = :id")
    void addSilverForClan(@Param("addSilverClan") Long addSilverClan, @Param("id") Long id);

}
