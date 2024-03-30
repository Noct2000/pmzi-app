package com.example.restapi.service;

import com.example.restapi.model.Role;

public interface RoleService extends CrudService<Role> {
    Role findByName(Role.RoleName roleName);
}