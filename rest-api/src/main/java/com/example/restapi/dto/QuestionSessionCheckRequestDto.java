package com.example.restapi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record QuestionSessionCheckRequestDto(
        @Min(1)
        @JsonProperty("first_question_id")
        Long firstQuestionId,
        @NotBlank
        @JsonProperty("first_answer")
        String firstAnswer,
        @Min(1)
        @JsonProperty("second_question_id")
        Long secondQuestionId,
        @NotBlank
        @JsonProperty("second_answer")
        String secondAnswer
) {
}
