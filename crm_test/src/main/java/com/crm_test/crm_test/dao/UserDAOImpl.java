package com.crm_test.crm_test.dao;

import com.crm_test.crm_test.entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO{

    private EntityManager entityManager;

    @Autowired
    public UserDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    /*FIND ALL*/
    @Override
    public List<User> findAll() {
        TypedQuery<User> query = entityManager.createQuery("FROM User", User.class);
        List<User> users = query.getResultList();
        return users;
    }

    @Override
    public User findById(int id) {
        return entityManager.find(User.class, id);
    }

    @Transactional
    @Override
    public void saveUser(User user) {
        entityManager.merge(user);
    }

    @Transactional
    @Override
    public void delete(int id) {
        User user = entityManager.find(User.class,id);
        entityManager.remove(user);
    }


    /*FIND BY USERNAME*/
    @Override
    public User findByUserName(String username) {
        TypedQuery<User> query = entityManager.createQuery("FROM User WHERE username =:username", User.class);
        query.setParameter("username", username);

        User user = null;
        try{
            user = query.getSingleResult();
        }catch (Exception exception){
            user = null;
        }

        return user;
    }

    /*SAVE*/
    @Transactional
    @Override
    public void save(User user) {
        entityManager.merge(user);
    }




}
