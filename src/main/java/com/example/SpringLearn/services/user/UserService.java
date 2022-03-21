package com.example.SpringLearn.services.user;

import com.example.SpringLearn.models.user.Role;
import com.example.SpringLearn.models.user.User;
import com.example.SpringLearn.repositories.user.UserRepo;
import com.example.SpringLearn.services.mail.MailSender;
import com.example.SpringLearn.services.person.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.logging.Logger;

@Service
public class UserService implements UserDetailsService {

    final UserRepo userRepo;
    final PasswordEncoder passwordEncoder;
    final MailSender mailSender;
    final PersonService personService;


    @Autowired
    public UserService(UserRepo userRepo, @Lazy PasswordEncoder passwordEncoder, MailSender mailSender, PersonService personService) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
        this.mailSender = mailSender;
        this.personService = personService;
    }

    public void plusUserPowerByTrain(Long id) {
        userRepo.plusUserPowerByTrain(id);
    }

    public void plusUserHealthByTrain(Long id) {
        userRepo.plusUserHealthByTrain(id);
    }

    public void plusUserManaByTrain(Long id) {
        userRepo.plusUserManaByTrain(id);
    }

    public void bossDamage(Long bossDamage, Long userId) {
        userRepo.bossDamage(bossDamage, userId);
    }

    public void updateBossDamage(Long bossDamage, Long userId) {
        userRepo.updateBossDamage(0L, userId);
    }


    public void minusUserManaAndHealthByVictory(Long id) {
        userRepo.minusManaUserVictory(id);
    }

    public void bossAttack(Long bossAttack, Long id) {
        userRepo.bossAttack(bossAttack, id);
    }

    public void updateToNullBossAttack(Long id) {
        userRepo.updateToNullBossAttack(id);
    }

    public void updateBossAttack(Long idBoss, Long id) {
        userRepo.updateBossAttack(idBoss,id);
    }





    public void minusUserManaAndHealthByDefeat(Long id) {
        userRepo.minusManaUserDefeat(id);
    }

    public void plusManaUser() {
        List<User> users = userRepo.findAll();

        for (User u : users) {
            if (u.getMana() < u.getMaxMana())
                userRepo.plusManaUser(u.getId());
        }
    }

    public void plusHealthUser() {
        List<User> users = userRepo.findAll();
        for (User u : users) {
            if (u.getHealth() < u.getMaxHealth())
                userRepo.plusUserHealth(u.getId());
        }
    }

    public List<User> findAll() {
        return userRepo.findAll();
    }


    public void addExpInRating(long exp,long expForClan ,long gold, long id) {

        userRepo.updateExpUser(exp,expForClan, gold, id);

    }

    public void addExpInClan(User user, Long expAddForClan) {
        if (user.getClan() != null) {
            userRepo.updateExpClan(user.getClan().getId(), expAddForClan);
        }
    }

    public void updateExpAndSilverUserAtCave(Long exp, Long silver, Long userId) {
        userRepo.updateExpAndSilverUserAtCave(exp, silver, userId);
    }

    public boolean checkUsername(String name) {
        return userRepo.findAll().stream().anyMatch(user -> user.getUsername().equals(name));
    }

    public User findUserById(Long id) {
        return userRepo.findAll().stream().filter(user -> user.getId() == id).findFirst().orElseThrow();
    }

    public Optional<User> findUserByIdt(Long id) {
        return userRepo.findById(id);
    }

    public Optional<User> findUserByEmail(String email) {
        return userRepo.findAll().stream().filter(user -> user.getEmail().equals(email)).findFirst();

    }

    public Optional<User> findUserByName(String name) {
        return userRepo.findAll().stream().filter(user -> user.getUsername().equals(name)).findFirst();
    }


    public void updateAvatar(String path, User user) {
        userRepo.updateAvatar(path, user.getId());
    }


    public void fileDelete(User user) {
        userRepo.deleteAvatar(user.getId());
    }

    public void uploadAvatar(MultipartFile multipartFile, User user) {
        Logger log = Logger.getLogger(PersonService.class.getSimpleName());
        try {
            byte[] bytes = multipartFile.getBytes();
            String s = UUID.randomUUID().toString().substring(0, 10);

            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(
                    new FileOutputStream("C:\\Users\\Nikolay\\Desktop\\upload\\" + s + ".png"));

            bufferedOutputStream.write(bytes);
            bufferedOutputStream.close();

            updateAvatar(s + ".png", user);
            log.info("Файл загружен успешно");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void addUser(String email, String name, String pass) {
        User user = new User();
        user.setUsername(name);
        user.setEmail(email);
        user.setPathToAvatar("ava.png");
        user.setExp(0L);
        user.setExpy(36L);
        user.setActiveCode(false);
        user.setUserLevel(1L);
        user.setUserGold(0L);
        user.setSkill(0L);
        user.setSilver(5000L);
        user.setMana(1000L);
        user.setSkillMana(0L);
        user.setSkillMaxMana(100L);
        user.setSkillHealth(0L);
        user.setSkillMaxHealth(100L);
        user.setSkillPower(0L);
        user.setSkillMaxPower(100L);
        user.setHealth(500L);
        user.setPower(200L);
        user.setMaxMana(1000L);
        user.setMaxHealth(500L);
        user.setPricePower(100L);
        user.setPriceHealth(100L);
        user.setPriceMana(100L);
        user.setPassword(passwordEncoder.encode(pass));
        user.setActivateMailCode(UUID.randomUUID().toString());
        user.setActive(true);

        user.setExpForClan(0L);

        sendEmail(user);

        user.setRoles(Collections.
                singleton(Role.USER));
        userRepo.save(user);
    }


    private void sendEmail(User user) {
        if (!user.getEmail().isEmpty()) {
            String message = String.format("Hello %s, your activate code to link http://192.168.0.3:8080/active/%s",
                    user.getUsername(),
                    user.getActivateMailCode()
            );
            mailSender.sendSimpleMessage(user.getEmail(), "Activation code", message);
        }
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return findUserByEmail(email).get();
    }

    public void updateUser(String username, long id) {
        userRepo.updateName(username, id);
    }

    public boolean updateUsrName(User user, String name) {
        if ((!user.getUsername().equals(name) && findUserByName(name).isEmpty())) {
            user.setUsername(name);
            return true;
        }
        return false;
    }

    public boolean updateUsrEmail(User user, String email) {
        if (!user.getEmail().equals(email) && findUserByEmail(email).isEmpty()
        ) {
            user.setEmail(email);
            user.setActiveCode(false);
            sendEmail(user);
            return true;
        }
        return false;
    }

    @Transactional
    public void saveUser(User user) {
        userRepo.save(user);
    }

    public double expBar(User us) {

        double exp_progress = 100 / (exp(us.getUserLevel()) / (double) us.getExp());

        if (exp_progress > 100) {
            exp_progress = 100;
        }

        return Math.floor(exp_progress);
    }

    public void updateLevelUser(User us) {


        if (us.getUserLevel() < 150 && us.getExp() >= us.getExpy()) {
            userRepo.updateUserLevel(us.getId(), (long) exp(us.getUserLevel() + 1));
        }
    }

    public static int exp(long lv) {
        int exp = 0;
        int lvl = (int) lv;
        switch (lvl) {
            case 1 -> exp = 36;
            case 2 -> exp = 72;
            case 3 -> exp = 216;
            case 4 -> exp = 432;
            case 5 -> exp = 720;
            case 6 -> exp = 1080;
            case 7 -> exp = 1512;
            case 8 -> exp = 2016;
            case 9 -> exp = 2592;
            case 10 -> exp = 3240;
            case 11 -> exp = 3960;
            case 12 -> exp = 4752;
            case 13 -> exp = 5616;
            case 14 -> exp = 6552;
            case 15 -> exp = 7560;
            case 16 -> exp = 8640;
            case 17 -> exp = 9792;
            case 18 -> exp = 11016;
            case 19 -> exp = 12312;
            case 20 -> exp = 13680;
            case 21 -> exp = 15120;
            case 22 -> exp = 16632;
            case 23 -> exp = 18216;
            case 24 -> exp = 19872;
            case 25 -> exp = 21600;
            case 26 -> exp = 23400;
            case 27 -> exp = 25272;
            case 28 -> exp = 27216;
            case 29 -> exp = 29232;
            case 30 -> exp = 31320;
            case 31 -> exp = 33480;
            case 32 -> exp = 35712;
            case 33 -> exp = 38016;
            case 34 -> exp = 40392;
            case 35 -> exp = 42840;
            case 36 -> exp = 45360;
            case 37 -> exp = 47952;
            case 38 -> exp = 50616;
            case 39 -> exp = 53352;
            case 40 -> exp = 56160;
            case 41 -> exp = 59040;
            case 42 -> exp = 61992;
            case 43 -> exp = 65016;
            case 44 -> exp = 68112;
            case 45 -> exp = 71280;
            case 46 -> exp = 74520;
            case 47 -> exp = 77832;
            case 48 -> exp = 81216;
            case 49 -> exp = 84672;
            case 50 -> exp = 88200;
        }
        return exp;
    }
}
