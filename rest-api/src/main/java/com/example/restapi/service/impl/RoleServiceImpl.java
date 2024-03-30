package com.example.restapi.service.impl;

import com.example.restapi.model.Role;
import com.example.restapi.repository.RoleRepository;
import com.example.restapi.service.RoleService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl extends CrudServiceImpl<Role> implements RoleService {
    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        super(roleRepository, Role.class.getSimpleName());
        this.roleRepository = roleRepository;
    }

    @Override
    public Role findByName(Role.RoleName roleName) {
        return roleRepository.findRoleByName(roleName).orElseThrow(
                () -> new EntityNotFoundException("No role by roleName: " + roleName.name())
        );
    }
}
