package com.crm_test.crm_test.dao;

import com.crm_test.crm_test.entity.Role;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

@Repository
public class RoleDAOImpl implements RoleDAO{

    private EntityManager entityManager;

    @Override
    public Role findRoleByName(String roleName) {

        TypedQuery<Role> query = entityManager.createQuery("FROM Role WHERE name =:role", Role.class);
        query.setParameter("role", roleName);

        Role role = null;

        try{
            role = query.getSingleResult();
        }catch (Exception exception){
            role = null;
        }

        return role;
    }
}
