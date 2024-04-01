package com.example.restapi.dto;

import java.time.LocalDateTime;
import java.util.List;

public record BaseResponseDto(
        List<String> messages,
        LocalDateTime timestamp
) {
}
