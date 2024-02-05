package com.crm_test.crm_test.dao;


import com.crm_test.crm_test.entity.User;

import java.util.List;

public interface UserDAO {
    List<User> findAll();
    User findByUserName(String username);
    void save(User user);


}
