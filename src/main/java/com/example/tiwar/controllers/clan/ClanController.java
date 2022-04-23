package com.example.tiwar.controllers.clan;

import com.example.tiwar.models.chat.Chat;
import com.example.tiwar.models.clan.Clan;
import com.example.tiwar.models.user.User;
import com.example.tiwar.repositories.chat.ChatRepository;
import com.example.tiwar.services.clan.ClanService;
import com.example.tiwar.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/clan")
public class ClanController {

    final UserService userService;
    final ClanService clanService;
    final ChatRepository chatRepository;

    @Autowired
    public ClanController(UserService userService, ClanService clanService, ChatRepository chatRepository) {
        this.userService = userService;
        this.clanService = clanService;
        this.chatRepository = chatRepository;
    }


    @GetMapping
    public String getClan(
            Model model,
            @AuthenticationPrincipal User user
    ) {
        Optional<User> userByIdt = userService.findUserByIdt(user.getId());

        Clan clan = null;
        if(userByIdt.get().getClan() != null) {
            clan = userByIdt.get().getClan();
            List<User> clanUsers = clan.getUsers().stream()
                    .sorted((u1, u2) -> u2.getExpForClan().compareTo(u1.getExpForClan())).collect(Collectors.toList());
            model.addAttribute("clanGetUsers", clanUsers);
            clanService.updateLevelClan(clan.getId());
            model.addAttribute("expClan", clanService.expBarClan(clan));
            model.addAttribute("expPercentClan", Math.floor(((double) clan.getExp() / clan.getExpy()) * 100));

        }

        model.addAttribute("clan", clan);
        model.addAttribute("us", userByIdt.get());
        model.addAttribute("exp", userService.expBar(userByIdt.get()));



        return "clan/clan";

    }

    @GetMapping("/{id}")
    public String getClanById(
            @AuthenticationPrincipal User user,
            @PathVariable("id") Long id,
            Model model
        ) {
        Optional<Clan> clanById = clanService.findClanById(id);
        User auth = userService.findUserById(user.getId());
        if(auth.getClan() != null) {
            if (auth.getClan().equals(clanById.get())) {
                return "redirect:/clan";
            }
        }

        List<User> clanUsers = clanById.get().getUsers().stream()
                .sorted((u1, u2) -> u2.getExpForClan().compareTo(u1.getExpForClan())).collect(Collectors.toList());

        Optional<User> ownerClan = clanUsers.stream()
                .filter(u -> u.getClan().getOwnerId() == clanById.get().getOwnerId()).findFirst();

        model.addAttribute("clan", clanById.get());
        model.addAttribute("clanGetUsers", clanUsers);
        model.addAttribute("ownerClan", ownerClan.get());
        model.addAttribute("user", userService.findUserById(user.getId()));
        model.addAttribute("us", userService.findUserById(user.getId()));
        model.addAttribute("expClan", clanService.expBarClan(clanById.get()));
        model.addAttribute("exp", userService.expBar(userService.findUserByIdt(user.getId()).get()));
        model.addAttribute("expPercentClan", Math.floor(((double) clanById.get().getExp() / clanById.get().getExpy()) * 100));



        return "clan/clanPage";
    }

    @GetMapping("/medals/{id}")
    public String getMedals(
            @PathVariable("id") Long id,
            @AuthenticationPrincipal User user,
            Model model
    ) {
        User userById = userService.findUserById(user.getId());
        Clan clan = clanService.findClanById(id).get();

        model.addAttribute("us", userById);
        model.addAttribute("clan", clan);
        model.addAttribute("exp", userService.expBar(userService.findUserByIdt(user.getId()).get()));

        return "clan/clanMedals";
    }

    @GetMapping("/{id}/money")
    public String getClanMoney(
            @AuthenticationPrincipal User user,
            @PathVariable("id") Long id,
            Model model
    ) {

        User userById = userService.findUserById(user.getId());
        if(userById.getClan().getId() == id) {
            Clan clan = clanService.findClanById(userById.getClan().getId()).get();

            model.addAttribute("us", userById);
            model.addAttribute("clan", clan);
            model.addAttribute("exp", userService.expBar(userService.findUserByIdt(user.getId()).get()));
        } else {
            return "redirect:/";
        }
        return "clan/clanMoney";
    }

    @PostMapping("/{id}/addMoney")
    public String addMoneyClan(
            @AuthenticationPrincipal User user,
            @PathVariable("id") Long id,
            @RequestParam("silver") Long silver,
            @RequestParam("gold") Long gold,
            Model model
    ) {
        User userById = userService.findUserById(user.getId());
        Clan clan = userById.getClan();

        if(userById.getClan().getId() == id) {
            if(userById.getUserGold() > gold) {
                clanService.addGoldInClan(gold, clan.getId());
                userService.addGoldForClan(gold, userById.getId());
            }
            if(userById.getSilver() > silver) {
                clanService.addSilverInClan(silver, clan.getId());
                userService.addSilverForClan(silver, userById.getId());
            }

        } else {
            return "redirect:/";
        }


        return "redirect:/clan/" + userById.getClan().getId() + "/money";
    }


    @PostMapping("/create")
    public String clanCreate(
            @AuthenticationPrincipal User authUser,
            @RequestParam("title") String title
    ) {
        User userById = userService.findUserById(authUser.getId());
        if(userById.getClan() == null && userById.getGold() >= 2500) {

            Clan clan = new Clan.Builder(title, userById.getId(), List.of(userById)).build();

            Clan clan1 = clanService.saveClan(clan);
            userById.setClan(clan1);

            Chat chat = new Chat("ClanChat" + clan1.getId());
            chatRepository.save(chat);

            userService.minusGoldForClanCreate(userById.getId());
            userService.saveUser(userById);
        }

        return "redirect:/clan";
    }

    @PostMapping("/subscribe")
    public String subscribe(
            @AuthenticationPrincipal User user,
            @RequestParam("id") Long id
    ) {
        Optional<Clan> clanById = clanService.findClanById(id);
        User userById = userService.findUserById(user.getId());

        clanService.saveClan(clanById.get());
        userById.setClanRequest(clanById.get());
        userService.saveUser(userById);

        return "redirect:/clan/" + id;
    }

    @PostMapping("/unsubscribe")
    public String unsubscribe(
            @AuthenticationPrincipal User user
    ) {
        User userById = userService.findUserById(user.getId());
        Long id = userById.getClan().getId();
        if(!Objects.equals(userById.getId(), userById.getClan().getOwnerId())) {
            userById.setClan(null);
            userById.setBossDamage(null);
            userById.setIdBossAttack(null);
            userById.setExpForClan(0L);
            userById.setAmountGoldForClan(0L);
            userById.setAmountSilverForClan(0L);
            userService.saveUser(userById);
        } else {
            System.out.println("Создатель");

            Clan clan = clanService.findClanById(id).get();
            List<User> userList = clan.getUsers();

            Chat chat = chatRepository.findByTitle("ClanChat" + clan.getId());

            chatRepository.delete(chat);

            for (User us : userList) {
                us.setClan(null);
                us.setIdBossAttack(null);
                us.setBossDamage(null);
                us.setAmountGoldForClan(0L);
                us.setAmountSilverForClan(0L);
                us.setExpForClan(0L);
                userService.saveUser(us);
            }
            clanService.deleteClan(clan);

        }
        return "redirect:/clan";
    }


}
