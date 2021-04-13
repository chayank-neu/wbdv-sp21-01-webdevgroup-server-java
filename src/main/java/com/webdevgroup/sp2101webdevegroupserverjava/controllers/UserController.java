package com.webdevgroup.sp2101webdevegroupserverjava.controllers;

import com.webdevgroup.sp2101webdevegroupserverjava.models.User;
import com.webdevgroup.sp2101webdevegroupserverjava.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    UserService service;

    @PostMapping("/api/register/{name}/{username}/{password}/{email}")
    public User register(
            @PathVariable("username") String username,
            @PathVariable("password") String password,
            @PathVariable("name") String name,
            @PathVariable("email") String email,
            HttpSession session) {
        User user = new User(name, username, password, email);
        user.setUsername(username);
        user.setPassword(password);
        session.setAttribute("currentUser", user);
        service.createUser(name, username, password, email);
        System.out.println("this line is reached");
        System.out.println(username);
        System.out.println(password);
        return user;
    }

    @GetMapping("/api/profile")
    public User profile(HttpSession session) {
        User currentUser = (User)
                session.getAttribute("currentUser");
        return currentUser;
    }

    @GetMapping("/api/logout")
    public void logout
            (HttpSession session) {
        session.invalidate();
    }

    //    //    TODO: This should actually use Post, not Get
    @GetMapping("/api/login/{username}/{password}")
    public boolean login(
            @PathVariable("username") String username,
            @PathVariable("password") String password,
            HttpSession session) {
        User user = service.findUserByUserName(username);
        System.out.println(username);
        System.out.println(password);
        System.out.println(user.getPassword().equals(password));
        if (user.getUsername().equals(username)
                && user.getPassword().equals(password)) {
            session.setAttribute("currentUser", user);
        }
        return false;
    }



//    @GetMapping("/api/session/set/{attr}/{value}")
//    public String setSessionAttribute(
//            @PathVariable("attr") String attr,
//            @PathVariable("value") String value,
//            HttpSession session) {
//        session.setAttribute(attr, value);
//        return attr + " = " + value;
//    }
//
//    @GetMapping("/api/session/get/{attr}")
//    public String getSessionAttribute(
//            @PathVariable("attr") String attr,
//            HttpSession session) {
//        return (String) session.getAttribute(attr);
//    }
//
//    @GetMapping("/api/session/invalidate")
//    public String invalidateSession(
//            HttpSession session) {
//        session.invalidate();
//        return "session invalidated";
//    }

}