package com.example.restapi.dto;

public record LoginResponseDto(
        String accessToken,
        String refreshToken
) {
}
