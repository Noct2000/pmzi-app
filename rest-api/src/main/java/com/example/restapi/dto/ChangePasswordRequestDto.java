package com.example.restapi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record ChangePasswordRequestDto(
        @NotBlank
        @JsonProperty("old_password")
        String oldPassword,
        @Pattern(regexp = ".*[a-zA-Z]+.*") // is it has letter
        @Pattern(regexp = ".*\\d+.*") // is it has digit
        @JsonProperty("new_password")
        String newPassword
) {
}
