package com.example.restapi.controller;

import com.example.restapi.dto.LoginRequestDto;
import com.example.restapi.dto.LoginResponseDto;
import com.example.restapi.service.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(
            @Valid @RequestBody LoginRequestDto loginRequestDto
    ) {
        Optional<LoginResponseDto> loginResponseDto = authenticationService
                .login(loginRequestDto.username(), loginRequestDto.password());
        return getLoginResponseDto(loginResponseDto);
    }

    @PostMapping("/refresh")
    public ResponseEntity<LoginResponseDto> refresh(@RequestBody String refreshToken) {
        Optional<LoginResponseDto> loginResponseDto = authenticationService.login(refreshToken);
        return getLoginResponseDto(loginResponseDto);
    }

    private ResponseEntity<LoginResponseDto> getLoginResponseDto(
            Optional<LoginResponseDto> loginResponseDto
    ) {
        return loginResponseDto.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.FORBIDDEN).build());
    }
}
