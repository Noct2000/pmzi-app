package com.example.restapi.mapper;

import com.example.restapi.dto.CreateUserRequestDto;
import com.example.restapi.dto.UserResponseDto;
import com.example.restapi.model.Role;
import com.example.restapi.model.User;
import com.example.restapi.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class UserMapper {
    private final RoleService roleService;

    public User toModel(CreateUserRequestDto createUserRequestDto) {
        Role demoRole = roleService.findByName(Role.RoleName.DEMO);

        return new User()
                .setUsername(createUserRequestDto.username())
                .setPassword(createUserRequestDto.password())
                .setRoles(Set.of(demoRole));
    }

    public UserResponseDto toResponseDto(User user) {
        Set<Role.RoleName> roleNames = user.getRoles().stream()
                .map(Role::getName)
                .collect(Collectors.toSet());
        return new UserResponseDto(
                user.getId(),
                user.getUsername(),
                roleNames,
                user.getBlocked(),
                user.getPassword()
        );
    }
}
