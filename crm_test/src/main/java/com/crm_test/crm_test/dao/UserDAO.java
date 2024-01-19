package com.crm_test.crm_test.dao;


import com.crm_test.crm_test.entity.User;

public interface UserDAO {
    User findByUserName(String username);

    void save(User user);
}
