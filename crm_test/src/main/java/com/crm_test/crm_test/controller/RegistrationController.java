package com.crm_test.crm_test.controller;

import com.crm_test.crm_test.entity.User;
import com.crm_test.crm_test.service.UserService;
import com.crm_test.crm_test.user.WebUser;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;

@Controller
@RequestMapping("/register")
public class RegistrationController {

    private Logger logger = Logger.getLogger( getClass().getName() );
    private UserService userService;

    @Autowired
    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder){
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor( true );
        webDataBinder.registerCustomEditor( String.class, stringTrimmerEditor );
    }

    @GetMapping("/show-registration-form")
    public  String showRegistrationForm(Model model){
        model.addAttribute( "webUser", new WebUser() );
        return "register/registration-form";
    }

    @PostMapping("process-registration-form")
    public String processRegistrationForm( @Valid @ModelAttribute("webUser") WebUser webUser, BindingResult bindingResult,
            HttpSession httpSession, Model model){

        String username = webUser.getUsername();
        logger.info( "Processing registration form for: " + username );

        /* form validation */
        if ( bindingResult.hasErrors() ){
            return "register/registration-form";
        }

        /* check database if user already exists */
        User user = userService.findByUserName(username);
        if ( user!= null ){
            model.addAttribute( "webUser", new WebUser() );
            model.addAttribute( "registrationError", "User name already exists" );

            logger.info( " User name already exists " );
            return "register/registration-form";
        }

        /* create user account and store it in db */
        userService.save(webUser);

        logger.info( "Successfully created user: " + username );

        /* place user in the http session for later use */
        httpSession.setAttribute( "user", webUser );

        return "register/registration-confirmation";
    }
}
