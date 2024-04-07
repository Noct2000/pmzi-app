package com.example.restapi.dto;

public record QuestionResponseDto(
        Long id,
        String question,
        String answer
) {
}
