package com.example.restapi.service;

import com.example.restapi.dto.LoginResponseDto;
import java.util.Optional;

public interface AuthenticationService {
    Optional<LoginResponseDto> login(String login, String password);

    Optional<LoginResponseDto> login(String refreshToken);
}
