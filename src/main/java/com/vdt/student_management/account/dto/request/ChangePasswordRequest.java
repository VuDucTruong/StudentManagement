package com.vdt.student_management.account.dto.request;

public record ChangePasswordRequest(
    String newPassword,
    String confirmPassword
) {

}
