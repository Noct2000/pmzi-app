package com.example.restapi.dto;

import com.example.restapi.model.Role;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import java.util.Set;

public record ChangeUserRolesDto(
        @Min(1)
        @JsonProperty("user_id")
        Long userId,
        @NotEmpty
        Set<Role.RoleName> roles
) {
}
