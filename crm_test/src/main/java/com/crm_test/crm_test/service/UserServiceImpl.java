package com.crm_test.crm_test.service;

import com.crm_test.crm_test.dao.RoleDAO;
import com.crm_test.crm_test.dao.UserDAO;
import com.crm_test.crm_test.entity.Role;
import com.crm_test.crm_test.entity.User;
import com.crm_test.crm_test.user.WebUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

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

    /* FIND ALL */
    @Override
    public List<User> findAll() {
        return userDAO.findAll();
    }

    /* FIND BY USERNAME */
    @Override
    public User findByUserName(String username) {
        return userDAO.findByUserName(username);
    }

    /* SAVE */
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



    /* pass the roles to the  SimpleGrantedAuthority type*/
    private Collection<SimpleGrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();

        for (Role role : roles){
            SimpleGrantedAuthority authority = new SimpleGrantedAuthority( role.getName() );
            authorities.add(authority);
        }

        return authorities;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDAO.findByUserName(username);

        if (user == null) throw new UsernameNotFoundException("Invalid username or password");
        Collection<SimpleGrantedAuthority> authorities = mapRolesToAuthorities( user.getRoles() );

        return new org.springframework.security.core.userdetails.User( user.getUsername(), user.getPassword(), authorities );
    }


}
