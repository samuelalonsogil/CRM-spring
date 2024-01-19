package com.crm_test.crm_test.security.config;

import com.crm_test.crm_test.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
public class SecurityConfig {


    /* bcrypt password encoder */
    @Bean
    public BCryptPasswordEncoder passWordEncoder(){
        return new BCryptPasswordEncoder();
    }

    /* provides access to the database for creating users */
    @Bean
    public DaoAuthenticationProvider authenticationProvider(UserService userService){

        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService( userService );
        daoAuthenticationProvider.setPasswordEncoder( passWordEncoder() );

        return daoAuthenticationProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity,
                                                   AuthenticationSuccessHandler authenticationSuccessHandler) throws Exception{
        httpSecurity.authorizeHttpRequests( configurator ->
                configurator
                        .requestMatchers("/").hasRole("USER")
                        .requestMatchers("/users/**").hasRole("ADMIN")
                        .requestMatchers("register/**").permitAll()
                        .anyRequest().authenticated()
        )
                .formLogin( form->
                        form
                                .loginPage("/loginPage")
                                .loginProcessingUrl("/authenticateUser")
                                .successHandler(authenticationSuccessHandler)
                                .permitAll()

                ).logout( logout-> logout.permitAll() )
                .exceptionHandling( configurator ->
                        configurator.accessDeniedPage("/access-denied") )
        ;

        return httpSecurity.build();

    }
}
