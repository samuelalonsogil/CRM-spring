package com.crm_test.crm_test.service;

import com.crm_test.crm_test.entity.User;
import com.crm_test.crm_test.user.WebUser;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    public User findByUserName(String username);

    void save(WebUser webUser);
}
