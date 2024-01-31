package com.crm_test.crm_test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/loginPage")
    public String showLoginPage(){
        return "login-page";
    }

    @GetMapping("/access-denied-page")
    public String showAccessDeniedPage(){
        return "access-denied-page";
    }
}
