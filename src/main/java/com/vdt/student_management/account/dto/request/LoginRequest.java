package com.vdt.student_management.account.dto.request;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record LoginRequest(
        @Size(min = 6, max = 40, message = "INVALID_USERNAME") String username,
        @Pattern(regexp = "(?=.*[a-zA-Z])(?=.*[^a-zA-Z0-9\\s])[\\S]{6,40}", message = "INVALID_PASSWORD")
                String password) {}
