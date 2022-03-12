package com.example.SpringLearn.rest;

import com.example.SpringLearn.models.user.User;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestRest {

//    @RequestMapping(value = "/test", method = RequestMethod.GET)
//    public Object test(@AuthenticationPrincipal User user) {
//
//        SecurityContext securityContext = SecurityContextHolder.getContext();
//        Object user1 = securityContext.getAuthentication().getPrincipal();
//
//        return user1;
//    }

}
