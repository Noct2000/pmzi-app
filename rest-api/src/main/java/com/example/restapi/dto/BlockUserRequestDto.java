package com.example.restapi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record BlockUserRequestDto(
        @NotNull
        Boolean status,
        @Min(1)
        @JsonProperty("user_id")
        Long userId
) {
}
