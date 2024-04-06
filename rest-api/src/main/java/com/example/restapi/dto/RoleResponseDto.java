package com.example.restapi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record RoleResponseDto(
        Long id,
        @JsonProperty("role_name")
        String roleName
) {
}
