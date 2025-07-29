package com.vdt.student_management.account.service;

import com.vdt.student_management.account.dto.request.ChangePasswordRequest;
import com.vdt.student_management.account.dto.request.LoginRequest;
import com.vdt.student_management.account.dto.response.AccountResponse;

public interface AuthService {
  AccountResponse login(LoginRequest loginRequest);

  void changePassword(ChangePasswordRequest changePasswordRequest);

  void logout();

  AccountResponse refreshToken(String refreshToken);
}
