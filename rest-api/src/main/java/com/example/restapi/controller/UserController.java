package com.example.restapi.controller;

import com.example.restapi.dto.BlockUserRequestDto;
import com.example.restapi.dto.ChangePasswordRequestDto;
import com.example.restapi.dto.ChangeUserRolesDto;
import com.example.restapi.dto.CreateUserRequestDto;
import com.example.restapi.dto.QuestionResponseDto;
import com.example.restapi.dto.UserResponseDto;
import com.example.restapi.mapper.QuestionMapper;
import com.example.restapi.mapper.UserMapper;
import com.example.restapi.model.User;
import com.example.restapi.service.QuestionService;
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
import java.util.Map;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;
    private final QuestionService questionService;
    private final QuestionMapper questionMapper;

    @PostMapping
    public UserResponseDto createUser(
            @RequestBody
            @Valid
            CreateUserRequestDto createUserRequestDto
    ) {
        User user = userMapper.toModel(createUserRequestDto);
        Map<String, String> questionAnswerMap = Map.of(
                createUserRequestDto.firstQuestion(), createUserRequestDto.firstAnswer(),
                createUserRequestDto.secondQuestion(), createUserRequestDto.secondAnswer()
        );
        userService.createNewUser(user, questionAnswerMap);
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

    @GetMapping("/questions")
    public List<QuestionResponseDto> getQuestionsForCurrentUser(Authentication authentication) {
        return questionService.findAllByUsername(authentication.getName()).stream()
                .map(questionMapper::toResponseDto)
                .toList();
    }

    @PatchMapping("/block")
    public ResponseEntity<Void> changeUserStatus(
            @RequestBody
            @Valid
            BlockUserRequestDto blockUserRequestDto
    ) {
        userService.changeBlockedStatus(blockUserRequestDto.userId(), blockUserRequestDto.status());
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PatchMapping("/roles")
    public UserResponseDto changeUserRoles(
            @RequestBody
            @Valid
            ChangeUserRolesDto changeUserRolesDto
    ) {
        User user = userService.changeRoles(changeUserRolesDto.userId(), changeUserRolesDto.roles());
        return userMapper.toResponseDto(user);
    }
}
