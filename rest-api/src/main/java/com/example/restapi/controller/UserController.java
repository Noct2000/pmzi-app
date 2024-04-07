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
import lombok.extern.log4j.Log4j2;
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
@Log4j2
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
        log.info("User with username {} was registered", createUserRequestDto.username());
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
        log.info("User with username: {} changed own password", authentication.getName());
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping
    public List<UserResponseDto> getAll(Authentication authentication) {
        log.info("User with username: {} getting all users", authentication.getName());
        return userService.findAll().stream()
                .map(userMapper::toResponseDto)
                .toList();
    }

    @GetMapping("/questions")
    public List<QuestionResponseDto> getQuestionsForCurrentUser(Authentication authentication) {
        log.info("User with username {} get questions for session check", authentication.getName());
        return questionService.findAllByUsername(authentication.getName()).stream()
                .map(questionMapper::toResponseDto)
                .toList();
    }

    @PatchMapping("/block")
    public ResponseEntity<Void> changeUserStatus(
            Authentication authentication,
            @RequestBody
            @Valid
            BlockUserRequestDto blockUserRequestDto
    ) {
        log.info("User with username: {} changed status of user with id: {} to {}",
                authentication.getName(),
                blockUserRequestDto.userId(),
                blockUserRequestDto.status()
                );
        userService.changeBlockedStatus(blockUserRequestDto.userId(), blockUserRequestDto.status());
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PatchMapping("/roles")
    public UserResponseDto changeUserRoles(
            Authentication authentication,
            @RequestBody
            @Valid
            ChangeUserRolesDto changeUserRolesDto
    ) {
        log.info("User with username {} changed roles of user with id: {}",
                authentication.getName(),
                changeUserRolesDto.userId()
                );
        User user = userService.changeRoles(changeUserRolesDto.userId(), changeUserRolesDto.roles());
        return userMapper.toResponseDto(user);
    }
}
