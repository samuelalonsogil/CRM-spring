package com.crm_test.crm_test.service;

import com.crm_test.crm_test.dao.RoleDAO;
import com.crm_test.crm_test.dao.UserDAO;
import com.crm_test.crm_test.entity.User;
import com.crm_test.crm_test.user.WebUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class UserServiceImpl implements UserService{

    private UserDAO userDAO;
    private RoleDAO roleDAO;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserServiceImpl(UserDAO userDAO, RoleDAO roleDAO, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userDAO = userDAO;
        this.roleDAO = roleDAO;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public User findByUserName(String username) {
        return userDAO.findByUserName(username);
    }

    @Override
    public void save(WebUser webUser) {
        User user = new User();

        user.setUsername( webUser.getUsername() );
        user.setPassword( bCryptPasswordEncoder.encode(webUser.getPassword() ) );
        user.setFirstName( webUser.getFirstName() );
        user.setLastName( webUser.getLastName() );
        user.setEmail( webUser.getEmail() );

        /* gives user default role of "USER" */
        user.setRoles(Arrays.asList( roleDAO.findRoleByName("ROLE_USER") ) );
        userDAO.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
}
