package com.example.restapi.dto;

import com.example.restapi.model.Role;
import java.util.Set;

public record UserResponseDto(
        Long id,
        String username,
        Set<Role.RoleName> roles,
        Boolean blocked,
        // must be removed but required for task
        String password
) {
}
