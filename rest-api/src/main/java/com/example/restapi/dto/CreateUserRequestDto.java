package com.example.restapi.dto;

import com.example.restapi.model.Role;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import java.util.Set;

public record CreateUserRequestDto(
        @NotBlank
        String username,
        @Pattern(regexp = ".*[a-zA-Z]+.*") // is it has letter
        @Pattern(regexp = ".*\\d+.*") // is it has digit
        String password,

        @NotBlank
        @JsonProperty("first_question")
        String firstQuestion,
        @NotBlank
        @JsonProperty("first_answer")
        String firstAnswer,
        @NotBlank
        @JsonProperty("second_question")
        String secondQuestion,
        @NotBlank
        @JsonProperty("second_answer")
        String secondAnswer
) {
}
