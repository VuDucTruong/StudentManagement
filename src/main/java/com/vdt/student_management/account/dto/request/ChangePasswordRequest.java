package com.vdt.student_management.account.dto.request;

import com.vdt.student_management.common.validator.PasswordMatches;
import jakarta.validation.constraints.Pattern;

@PasswordMatches
public record ChangePasswordRequest(
        @Pattern(regexp = "(?=.*[a-zA-Z])(?=.*[^a-zA-Z0-9\\s])[\\S]{6,40}", message = "INVALID_PASSWORD")
                String newPassword,
        @Pattern(regexp = "(?=.*[a-zA-Z])(?=.*[^a-zA-Z0-9\\s])[\\S]{6,40}", message = "INVALID_PASSWORD")
                String confirmPassword) {}
