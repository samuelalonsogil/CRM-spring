package com.crm_test.crm_test.security;

import com.crm_test.crm_test.entity.User;
import com.crm_test.crm_test.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Controller;

import java.io.IOException;

@Controller
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    /*
    This code will perform the following steps:
    - Retrieve the user from the database via the UserService
    - Place the user in the session
    - Forward to the home page
    */

    private UserService userService;

    public CustomAuthenticationSuccessHandler(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        System.out.println(" In CustomAuthenticationSuccessHandler ");
        String userName= authentication.getName();
        System.out.println("userName: " + userName);
        User user = userService.findByUserName(userName);

        /* place in the session */
        HttpSession httpSession = request.getSession();
        httpSession.setAttribute("user", user);

        /* forward to home page */
        response.sendRedirect(request.getContextPath() + "/");


    }
}
