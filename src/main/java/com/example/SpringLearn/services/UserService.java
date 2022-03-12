package com.example.SpringLearn.services;

import com.example.SpringLearn.models.Role;
import com.example.SpringLearn.models.User;
import com.example.SpringLearn.repositories.UserRepo;
import freemarker.template.utility.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Service
public class UserService implements UserDetailsService {

    final
    UserRepo userRepo;

    final PasswordEncoder passwordEncoder;

    final
    MailSender mailSender;

    public UserService(UserRepo userRepo, @Lazy PasswordEncoder passwordEncoder, MailSender mailSender) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
        this.mailSender = mailSender;
    }

    public List<User> findAll() {
        return userRepo.findAll();
    }

    public boolean checkUsername(String name) {
        return userRepo.findAll().stream().anyMatch(user -> user.getUsername().equals(name));
    }

    public User findUserById(Long id) {
        return  userRepo.findAll().stream().filter(user -> user.getId() == id).findFirst().orElseThrow();
    }

    public User findUser(String email) {
        return userRepo.findAll().stream().filter(user -> user.getEmail().equals(email)).findFirst().orElseThrow();

    }

    public void saveUser(User user) {
        userRepo.save(user);
    }

    public void addUser(String email,String name, String pass) {
        User user = new User();
        user.setUsername(name);
        user.setEmail(email);
        user.setActiveCode(false);
        user.setPassword(passwordEncoder.encode(pass));
        user.setActivateMailCode(UUID.randomUUID().toString());
        user.setActive(true);

        if(!user.getEmail().isEmpty()) {
            String message = String.format("Hello %s, your activate code to link http://localhost:8080/active/%s",
                    user.getUsername(),
                    user.getActivateMailCode()
                    );
            mailSender.sendSimpleMessage(user.getEmail(), "Activation code", message);
        }

        user.setRoles(Collections.singleton(Role.USER));
        userRepo.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return findUser(email);
    }

    public void updateUser(String username, long id) {
        userRepo.updateName(username, id);
    }
}
