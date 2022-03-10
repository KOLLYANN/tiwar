package com.example.SpringLearn.services;

import com.example.SpringLearn.models.Role;
import com.example.SpringLearn.models.User;
import com.example.SpringLearn.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    UserRepo userRepo;



    public User findUser(String name) {
        return userRepo.findAll().stream().filter(user -> user.getUsername().equals(name)).findFirst().orElseThrow();
    }

    public void addUser(String name, String pass) {
        User user = new User();
        user.setUsername(name);
        user.setPassword(pass);
        user.setActive(true);
        user.setRoles(Collections.singletonList(Role.USER));
        userRepo.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return findUser(username);
    }
}