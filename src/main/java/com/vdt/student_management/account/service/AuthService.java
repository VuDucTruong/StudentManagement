package com.vdt.student_management.account.service;

import com.vdt.student_management.account.dto.request.ChangePasswordRequest;
import com.vdt.student_management.account.dto.request.LoginRequest;
import com.vdt.student_management.account.dto.response.AccountResponse;
import com.vdt.student_management.common.enums.RoleType;

public interface AuthService {
    AccountResponse login(LoginRequest loginRequest);

    void changePassword(ChangePasswordRequest changePasswordRequest, String accessToken);

    AccountResponse refreshToken(String refreshToken);

    AccountResponse getMyAccount(String accessToken);

    boolean hasMinRole(RoleType roleType);

    void logout(String accessToken);
}
