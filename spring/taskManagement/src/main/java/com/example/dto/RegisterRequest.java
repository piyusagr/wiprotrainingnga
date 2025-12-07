package com.example.dto;

import jakarta.validation.constraints.NotBlank;
import com.example.enums.Role;

public record RegisterRequest(
    @NotBlank String username,
    @NotBlank String password,
    Role role
) {}
