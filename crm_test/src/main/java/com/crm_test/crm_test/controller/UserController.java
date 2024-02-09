package com.crm_test.crm_test.controller;

import com.crm_test.crm_test.entity.User;
import com.crm_test.crm_test.service.UserService;
import com.crm_test.crm_test.user.WebUser;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

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
        model.addAttribute( "user", user );
        return "user-detail";
    }

    @GetMapping("/show-form-edit")
    public String showUpdateForm(@RequestParam("id") int id, Model model){
        User user = userService.findById(id);
        model.addAttribute("user", user);
        return "update-user-form";
    }

    @PostMapping("/process-update-form")
    public String processUpdateForm(@Valid @ModelAttribute("user") User user, BindingResult bindingResult,
            HttpSession httpSession, Model model){

        if (bindingResult.hasErrors() ) return "update-user-form";
        userService.saveUser( user );
        httpSession.setAttribute( "user", user );

        return "update-confirmation";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("id") int id, Model model){
        userService.delete(id);
        return "redirect:/users-list ";
    }
}
