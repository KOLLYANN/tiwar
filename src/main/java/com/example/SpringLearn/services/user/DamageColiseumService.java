package com.example.SpringLearn.services.user;

import com.example.SpringLearn.models.user.DamageColiseum;
import com.example.SpringLearn.repositories.user.DamageColiseumRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DamageColiseumService {

    final DamageColiseumRepo damageColiseumRepo;


    public DamageColiseumService(DamageColiseumRepo damageColiseumRepo) {
        this.damageColiseumRepo = damageColiseumRepo;
    }

    public void saveDamage(DamageColiseum damageColiseum) {
        damageColiseumRepo.save(damageColiseum);
    }

    public void saveDamageAll(List<DamageColiseum> damageColiseum) {
        damageColiseumRepo.saveAllAndFlush(damageColiseum);
    }

    public void findDamage(DamageColiseum damageColiseum) {
        damageColiseumRepo.findById(damageColiseum.getId());
    }

    public List<DamageColiseum> findDamageAll() {
        return damageColiseumRepo.findAll();
    }

    public void deleteDamage(List<DamageColiseum> damageColiseum) {
        damageColiseumRepo.deleteAll(damageColiseum);
    }

    public void deleteDamageById(DamageColiseum damageColiseum) {
        damageColiseumRepo.deleteById(damageColiseum.getId());
    }


}
