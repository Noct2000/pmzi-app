package com.example.restapi.controller;

import com.example.restapi.dto.RoleResponseDto;
import com.example.restapi.mapper.RoleMapper;
import com.example.restapi.service.RoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/roles")
@RequiredArgsConstructor
@Log4j2
public class RoleController {
    private final RoleService roleService;
    private final RoleMapper roleMapper;

    @GetMapping
    public List<RoleResponseDto> getRoles(Authentication authentication) {
        log.info("User with username: {} getting all roles", authentication.getName());
        return roleService.findAll().stream()
                .map(roleMapper::toResponseDto)
                .toList();
    }

}
