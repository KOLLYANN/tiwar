package com.example.SpringLearn.services.boss;

import com.example.SpringLearn.models.Boss;
import com.example.SpringLearn.repositories.boss.BossRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class BossService {

    final BossRepo bossRepo;

    @Autowired
    public BossService(BossRepo bossRepo) {
        this.bossRepo = bossRepo;
    }


    public Optional<Boss> findBossById(Long id) {
        return bossRepo.findById(id);
    }

    public List<Boss> findAll() {
        return bossRepo.findAll();
    }

    public boolean existsBoss(Long id) {
       return bossRepo.existsById(id);
    }

    public void bossOneMinusHealth(Long minusHealth, Long id) {
        bossRepo.bossOneMinusHealth(minusHealth, id);
    }

    public void saveBoss(Boss boss) {
        bossRepo.save(boss);
    }

    @Transactional
    public void deleteBoss(Boss boss) {
        bossRepo.delete(boss);
    }


}
