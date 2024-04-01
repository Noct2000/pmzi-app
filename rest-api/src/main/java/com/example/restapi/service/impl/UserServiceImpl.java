package com.example.restapi.service.impl;

import com.example.restapi.dto.ChangePasswordRequestDto;
import com.example.restapi.exception.AuthenticationException;
import com.example.restapi.model.User;
import com.example.restapi.repository.UserRepository;
import com.example.restapi.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UserServiceImpl extends CrudServiceImpl<User> implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder
    ) {
        super(userRepository, User.class.getSimpleName());
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
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
}
