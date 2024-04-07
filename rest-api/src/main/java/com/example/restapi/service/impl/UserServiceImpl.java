package com.example.restapi.service.impl;

import com.example.restapi.dto.ChangePasswordRequestDto;
import com.example.restapi.exception.AuthenticationException;
import com.example.restapi.exception.UsernameDuplicationException;
import com.example.restapi.model.Question;
import com.example.restapi.model.Role;
import com.example.restapi.model.User;
import com.example.restapi.repository.UserRepository;
import com.example.restapi.service.QuestionService;
import com.example.restapi.service.RoleService;
import com.example.restapi.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl extends CrudServiceImpl<User> implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleService roleService;
    private final QuestionService questionService;

    public UserServiceImpl(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            RoleService roleService,
            QuestionService questionService
    ) {
        super(userRepository, User.class.getSimpleName());
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleService = roleService;
        this.questionService = questionService;
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }

    @Override
    @Transactional
    public void changeUserPassword(
            String username,
            ChangePasswordRequestDto changePasswordRequestDto
    ) {
        User user = findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("No user with username: " + username));

        if (!passwordEncoder.matches(changePasswordRequestDto.oldPassword(), user.getPassword())) {
           throw new AuthenticationException("Incorrect password for user: " + username);
        }
        user.setPassword(passwordEncoder.encode(changePasswordRequestDto.newPassword()));
        userRepository.save(user);
    }

    @Override
    @Transactional
    public User createNewUser(User user, Map<String, String> questionAnswerMap) {
        Optional<User> optionalUser = findByUsername(user.getUsername());
        if (optionalUser.isPresent()) {
            throw new UsernameDuplicationException("User with username: "
                    + user.getUsername()
                    + " already exists"
            );
        }
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        save(user);

        questionAnswerMap.forEach((key, value) -> questionService.save(
                new Question()
                        .setUser(user)
                        .setQuestion(key)
                        .setAnswer(value)
        ));

        return user;
    }

    @Override
    @Transactional
    public void changeBlockedStatus(Long userId, Boolean status) {
        User user = findById(userId);
        user.setBlocked(status);
        userRepository.save(user);
    }

    @Override
    @Transactional
    public User changeRoles(Long userId, Set<Role.RoleName> roleNames) {
        User user = findById(userId);
        Set<Role> roles = roleNames.stream().map(roleService::findByName).collect(Collectors.toSet());
        user.setRoles(roles);
        return save(user);
    }
}
