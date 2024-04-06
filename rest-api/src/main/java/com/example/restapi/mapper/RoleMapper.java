package com.example.restapi.mapper;

import com.example.restapi.dto.RoleResponseDto;
import com.example.restapi.model.Role;
import org.springframework.stereotype.Component;

@Component
public class RoleMapper {
    public RoleResponseDto toResponseDto(Role role) {
        return new RoleResponseDto(role.getId(), role.getName().name());
    }
}
