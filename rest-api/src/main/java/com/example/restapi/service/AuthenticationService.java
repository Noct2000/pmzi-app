package com.example.restapi.service;

import com.example.restapi.dto.LoginResponseDto;
import com.example.restapi.dto.QuestionSessionCheckRequestDto;
import java.util.Optional;

public interface AuthenticationService {
    Optional<LoginResponseDto> login(String login, String password);

    Optional<LoginResponseDto> checkSession(
            QuestionSessionCheckRequestDto questionSessionCheckRequestDto,
            String username
    );
}
