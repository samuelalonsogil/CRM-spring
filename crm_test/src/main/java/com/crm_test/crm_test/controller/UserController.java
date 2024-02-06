package com.crm_test.crm_test.controller;

import com.crm_test.crm_test.entity.User;
import com.crm_test.crm_test.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users-list")
    public String showUserList(Model model){
        List<User> users = userService.findAll();
        model.addAttribute("users", users);
        return "users-list";
    }

    @GetMapping("/find-user-by-name")
    public String findUserByName(String username, Model model){
        User user = userService.findByUserName(username);
        System.out.println("user: " + user);
        model.addAttribute( "user", user );
        return "user-detail";
    }
}
