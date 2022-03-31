package com.example.SpringLearn.services.clan;

import com.example.SpringLearn.models.clan.Clan;
import com.example.SpringLearn.models.user.User;
import com.example.SpringLearn.repositories.clan.ClanRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClanService {

    final ClanRepo clanRepo;

    @Autowired
    public ClanService(ClanRepo clanRepo) {
        this.clanRepo = clanRepo;
    }


    public Clan saveClan(Clan clan) {
        return clanRepo.save(clan);
    }

    public Optional<Clan> findClanById(Long id) {
        return clanRepo.findById(id);
    }

    public void deleteClan(Clan clan) {
        clanRepo.delete(clan);
    }

    public List<Clan> findAllClans() {
        return clanRepo.findAll();
    }

    public void addCountB1forClan(Long id) {
        clanRepo.addCountBossVictoryB1(id);
    }

    public void addCountB2forClan(Long id) {
        clanRepo.addCountBossVictoryB2(id);
    }

    public void addCountB3forClan(Long id) {
        clanRepo.addCountBossVictoryB3(id);
    }

    public void addCountB4forClan(Long id) {
        clanRepo.addCountBossVictoryB4(id);
    }

    public void addCountB5forClan(Long id) {
        clanRepo.addCountBossVictoryB5(id);
    }

    public void addCountB6forClan(Long id) {
        clanRepo.addCountBossVictoryB6(id);
    }

    public void addCountB7forClan(Long id) {
        clanRepo.addCountBossVictoryB7(id);
    }

    public void addCountB8forClan(Long id) {
        clanRepo.addCountBossVictoryB8(id);
    }

    public void addCountB9forClan(Long id) {
        clanRepo.addCountBossVictoryB9(id);
    }

    public void addCountB10forClan(Long id) {
        clanRepo.addCountBossVictoryB10(id);
    }

    public void addGoldInClan(Long addGold, Long id) {
        clanRepo.addGoldInClan(addGold, id);
    }

    public void addSilverInClan(Long addSilver, Long id) {
        clanRepo.addSilverInClan(addSilver, id);
    }

    public double expBarClan(Clan clan) {

        double exp_progress = 100 / (exp(clan.getLevel()) / (double) clan.getExp());

        if (exp_progress > 100) {
            exp_progress = 100;
        }
        return Math.floor(exp_progress);
    }

    public void updateLevelClan(Long id) {
        Optional<Clan> clan = clanRepo.findById(id);
        Clan c = clan.get();
        if (c.getLevel() < 150 && c.getExp() >= c.getExpy()) {
            clanRepo.updateClanLevel(c.getId(), (long) exp(c.getLevel() + 1));
        }
    }

    public static int exp(long lv) {
        int exp = 0;
        int lvl = (int) lv;
        switch (lvl) {
            case 1 -> exp = 360;
            case 2 -> exp = 720;
            case 3 -> exp = 2160;
            case 4 -> exp = 4320;
            case 5 -> exp = 7200;
            case 6 -> exp = 10800;
            case 7 -> exp = 15120;
            case 8 -> exp = 20160;
            case 9 -> exp = 25920;
            case 10 -> exp = 32400;
            case 11 -> exp = 39600;
            case 12 -> exp = 47520;
            case 13 -> exp = 56160;
            case 14 -> exp = 65520;
            case 15 -> exp = 75600;
            case 16 -> exp = 86400;
            case 17 -> exp = 97920;
            case 18 -> exp = 110160;
            case 19 -> exp = 123120;
            case 20 -> exp = 136800;
            case 21 -> exp = 151200;
            case 22 -> exp = 166320;
            case 23 -> exp = 182160;
            case 24 -> exp = 198720;
            case 25 -> exp = 216000;
        }
        return exp;
    }
}

