package com.example.SpringLearn.services.user;

import com.example.SpringLearn.models.thing.Thing;
import com.example.SpringLearn.models.user.Role;
import com.example.SpringLearn.models.user.User;
import com.example.SpringLearn.repositories.user.UserRepo;
import com.example.SpringLearn.services.mail.MailSender;
import com.example.SpringLearn.services.person.PersonService;
import com.example.SpringLearn.services.thing.ThingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;
import java.util.logging.Logger;

@Service
public class UserService implements UserDetailsService {

    final UserRepo userRepo;
    final PasswordEncoder passwordEncoder;
    final MailSender mailSender;
    final PersonService personService;
    final ThingService thingService;


    @Autowired
    public UserService(UserRepo userRepo, @Lazy PasswordEncoder passwordEncoder, MailSender mailSender, PersonService personService, ThingService thingService) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
        this.mailSender = mailSender;
        this.personService = personService;
        this.thingService = thingService;
    }

    public void buyThing(Long priceThing, Long id) {
        userRepo.buyThing(priceThing, id);
    }

    public void sellThing(Long priceThing, Long id) {
        userRepo.sellThing(priceThing, id);
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

    public void minusUserParametersThing(Long parameters, Long id) {
        userRepo.minusUserParametersThing(parameters, id);
    }

    public void plusUserParametersThing(Long parameters, Long id) {
        userRepo.plusUserParametersThing(parameters, id);
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

    public void addGoldForClan(Long addAmountGold, Long id) {
        userRepo.addGoldForClan(addAmountGold, id);
    }
    public void addSilverForClan(Long addAmountSilver, Long id) {
        userRepo.addSilverForClan(addAmountSilver, id);
    }

    public void minusGoldForClanCreate(Long id) {
        userRepo.minusGoldForClanCreate(id);
    }

    public void updateExpAndSilverUserAtCave(Long exp, Long silver, Long userId) {
        userRepo.updateExpAndSilverUserAtCave(exp, silver, userId);
    }

    public void addSkull(Long id) {
        userRepo.addSkull(id);
    }

    public void updateAttackColiseum(Long idUserAttack, Long id) {
        userRepo.updateAttackColiseum(idUserAttack, id);
    }

    public void updateStartAttackColiseum(Long flag, Long id) {
        userRepo.updateStartAttackColiseum(flag, id);
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
        user.setAmountGoldForClan(0L);
        user.setAmountSilverForClan(0L);
        user.setSkill(0L);
        user.setSilver(5000L);
        user.setMana(1000L);
        user.setSkull(0L);
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

        List<Thing> things = thingService.findAll();

        user.setExpForClan(0L);

        sendEmail(user);

        user.setRoles(Collections.singleton(Role.USER));
        userRepo.save(user);

        Thing th1 = null;
        Thing th2 = null;
        Thing th3 = null;
        Thing th4 = null;
        Thing th5 = null;
        Thing th6 = null;
        Thing th7 = null;

        for (Thing th : things) {
            if (th.getId() == 1) {
                th1 = new Thing(th.getTitle(), th.getParameters(), th.getPathImage(),
                        th.getGrade(), th.getPosition(), th.getSkillGrade(),
                        th.getPlace(), 1L, th.getPrice(), th.getQuality(), th.getBorder(), 0L, "11");
                th1.setUser(user);
            } else if (th.getId() == 2) {
                th2 = new Thing(th.getTitle(), th.getParameters(), th.getPathImage(),
                        th.getGrade(), th.getPosition(), th.getSkillGrade(),
                        th.getPlace(), 1L, th.getPrice(), th.getQuality(), th.getBorder(), 0L, "11");
                th2.setUser(user);
            } else if (th.getId() == 3) {
                th3 = new Thing(th.getTitle(), th.getParameters(), th.getPathImage(),
                        th.getGrade(), th.getPosition(), th.getSkillGrade(),
                        th.getPlace(), 1L, th.getPrice(), th.getQuality(), th.getBorder(), 0L, "11");
                th3.setUser(user);
            } else if (th.getId() == 4) {
                th4 = new Thing(th.getTitle(), th.getParameters(), th.getPathImage(),
                        th.getGrade(), th.getPosition(), th.getSkillGrade(),
                        th.getPlace(), 1L, th.getPrice(), th.getQuality(), th.getBorder(), 0L, "11");
                th4.setUser(user);
            } else if (th.getId() == 5) {
                th5 = new Thing(th.getTitle(), th.getParameters(), th.getPathImage(),
                        th.getGrade(), th.getPosition(), th.getSkillGrade(),
                        th.getPlace(), 1L, th.getPrice(), th.getQuality(), th.getBorder(), 0L, "11");
                th5.setUser(user);
            } else if (th.getId() == 6) {
                th6 = new Thing(th.getTitle(), th.getParameters(), th.getPathImage(),
                        th.getGrade(), th.getPosition(), th.getSkillGrade(),
                        th.getPlace(), 1L, th.getPrice(), th.getQuality(), th.getBorder(), 0L, "11");
                th6.setUser(user);
            } else if (th.getId() == 7) {
                th7 = new Thing(th.getTitle(), th.getParameters(), th.getPathImage(),
                        th.getGrade(), th.getPosition(), th.getSkillGrade(),
                        th.getPlace(), 1L, th.getPrice(), th.getQuality(), th.getBorder(), 0L, "11");
                th7.setUser(user);
            }
        }
        thingService.saveAllThing(List.of(th1, th2, th3, th4, th5, th6, th7));
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

    public void updateCampaign(Long flag, User user) {
        userRepo.updateCampaign(flag, user.getId());
    }

    public void timerTaskCampaign(User user) {

        User userById = userRepo.findById(user.getId()).get();

        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                if(userRepo.getCampaign(userById.getId()) != null) {
                    userRepo.addSkull(userById.getId());
                    System.out.println("ТУТТУТУТУТ");
                }
                userRepo.updateCampaign(null, userById.getId());
                userRepo.setTimeStartCampaign(0, userById.getId());
                userRepo.setTimeEndCampaign(0, userById.getId());
            }
        };
        Timer timer = new Timer();
        timer.schedule(timerTask,60000);
        userRepo.setTimeStartCampaign(new Date().getTime(), userById.getId());
        userRepo.setTimeEndCampaign(new Date().getTime() + 60000, userById.getId());

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
            case 51 -> exp = 91800;
            case 52 -> exp = 95472;
            case 53 -> exp = 99216;
            case 54 -> exp = 103032;
            case 55 -> exp = 106920;
            case 56 -> exp = 110880;
            case 57 -> exp = 114912;
            case 58 -> exp = 119016;
            case 59 -> exp = 123192;
            case 60 -> exp = 127440;
            case 61 -> exp = 131760;
            case 62 -> exp = 136152;
            case 63 -> exp = 140616;
            case 64 -> exp = 145152;
            case 65 -> exp = 149760;
            case 66 -> exp = 154440;
            case 67 -> exp = 159192;
            case 68 -> exp = 164016;
            case 69 -> exp = 168912;
            case 70 -> exp = 1738800;
            case 71 -> exp = 1789200;
            case 72 -> exp = 1840320;
            case 73 -> exp = 1892160;
            case 74 -> exp = 1944720;
            case 75 -> exp = 1998000;
            case 76 -> exp = 2052000;
            case 77 -> exp = 2106720;
            case 78 -> exp = 2162160;
            case 79 -> exp = 2218320;
            case 80 -> exp = 2275200;
            case 81 -> exp = 2332800;
            case 82 -> exp = 2391120;
            case 83 -> exp = 2450160;
            case 84 -> exp = 2509920;
            case 85 -> exp = 2570400;
            case 86 -> exp = 2631600;
            case 87 -> exp = 2693520;
            case 88 -> exp = 2756160;
            case 89 -> exp = 2819520;
            case 90 -> exp = 2883600;
            case 91 -> exp = 2948400;
            case 92 -> exp = 3013920;
            case 93 -> exp = 3080160;
            case 94 -> exp = 3147120;
            case 95 -> exp = 3214800;
            case 96 -> exp = 3283200;
            case 97 -> exp = 3352320;
            case 98 -> exp = 3422160;
            case 99 -> exp = 6848210;
            case 100 -> exp = 12147280;
        }
        return exp;
    }

    public User getUserRandom(User userById, UserService userService) {
        List<User> rndUsers = userService.findAll().stream()
                .filter(user1 -> user1.getStartAttackColiseum() != null
                        && user1.getIdAttackUserColiseum() == null
                        && !Objects.equals(user1.getId(), userById.getId()))
                .toList();

        User usrAttack = null;
        for (int i = 0; i < rndUsers.size(); i++) {
            if (!userById.getId().equals(rndUsers.get(i).getId())) {
                int rnd = new Random().nextInt(rndUsers.size());
                usrAttack = rndUsers.get(rnd);
            }
        }
        return usrAttack;
    }


}
