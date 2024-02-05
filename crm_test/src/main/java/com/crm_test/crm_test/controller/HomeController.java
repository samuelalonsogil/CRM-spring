package com.crm_test.crm_test.controller;

import com.crm_test.crm_test.entity.User;
import com.crm_test.crm_test.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    private UserService userService;

    @Autowired
    public HomeController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String showHome(){
        return "home-page";
    }

    @GetMapping("/users-list")
    public String showUserList(Model model){
        List<User> users = userService.findAll();
        model.addAttribute("users", users);
        return "users-list";
    }

}
