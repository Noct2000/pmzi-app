package com.example.restapi.controller;

import com.example.restapi.dto.ChangePasswordRequestDto;
import com.example.restapi.dto.CreateUserRequestDto;
import com.example.restapi.dto.UserResponseDto;
import com.example.restapi.mapper.UserMapper;
import com.example.restapi.model.User;
import com.example.restapi.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;

    @PostMapping
    public UserResponseDto createUser(
            @RequestBody
            @Valid
            CreateUserRequestDto createUserRequestDto
    ) {
        User user = userMapper.toModel(createUserRequestDto);
        userService.save(user);
        return userMapper.toResponseDto(user);
    }

    @PatchMapping("/change-password")
    public ResponseEntity<Void> changePassword(
            Authentication authentication,
            @RequestBody
            @Valid
            ChangePasswordRequestDto changePasswordRequestDto
            ) {
        userService.changeUserPassword(authentication.getName(), changePasswordRequestDto);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping
    public List<UserResponseDto> getAll() {
        return userService.findAll().stream()
                .map(userMapper::toResponseDto)
                .toList();
    }
}
