package com.example.restapi.service.impl;

import com.example.restapi.dto.LoginResponseDto;
import com.example.restapi.exception.AuthenticationException;
import com.example.restapi.jwt.JwtTokenProvider;
import com.example.restapi.model.User;
import com.example.restapi.service.AuthenticationService;
import com.example.restapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public Optional<LoginResponseDto> login(String username, String password) {
        Optional<User> user = userService.findByUsername(username);
        if (user.isEmpty() || !passwordEncoder.matches(password, user.get().getPassword())) {
            return Optional.empty();
        }
        if (user.get().getBlocked()) {
            throw new AuthenticationException("User with username: " + username + " blocked");
        }
        return Optional.of(getLoginResponse(user.get()));
    }

    @Override
    public Optional<LoginResponseDto> login(String refreshToken) {
        Optional<User> user = userService
                .findByUsername(jwtTokenProvider.getUsername(refreshToken));
        return user.map(this::getLoginResponse);
    }

    private LoginResponseDto getLoginResponse(User user) {
        String accessToken = jwtTokenProvider.createAccessToken(user.getUsername());
        String refreshToken = jwtTokenProvider.createRefreshToken(user.getUsername());
        return new LoginResponseDto(
                accessToken,
                refreshToken
        );
    }

}
