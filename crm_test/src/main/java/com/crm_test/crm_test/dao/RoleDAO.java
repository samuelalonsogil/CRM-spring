package com.crm_test.crm_test.dao;

import com.crm_test.crm_test.entity.Role;

public interface RoleDAO {
    Role findRoleByName(String role);

}
