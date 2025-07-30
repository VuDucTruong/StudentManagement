package com.vdt.student_management.account.controller;

import com.vdt.student_management.account.dto.request.ChangePasswordRequest;
import com.vdt.student_management.account.dto.request.LoginRequest;
import com.vdt.student_management.account.dto.response.AccountResponse;
import com.vdt.student_management.account.service.AuthService;
import com.vdt.student_management.account.service.TokenBlacklistService;
import com.vdt.student_management.common.dto.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/auth")
@RestController
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Tag(name = "Authorization", description = "Actions for authorization")
@Slf4j
public class AuthController {

  AuthService authService;
  TokenBlacklistService tokenBlacklistService;


  @GetMapping("/me")
  ApiResponse<AccountResponse> getCurrentUser(HttpServletRequest request) {

    String accessToken = getAccessToken(request);
    return ApiResponse.<AccountResponse>builder().code(200)
        .data(authService.getMyAccount(accessToken)).build();
  }

  @PostMapping("/login")
  ApiResponse<AccountResponse> login(
      @RequestBody LoginRequest loginRequest) {
    return
        ApiResponse.<AccountResponse>builder().code(200).data(authService.login(loginRequest))
            .build();
  }

  @PutMapping("/change-password")
  ApiResponse<Void> changePassword(@RequestBody ChangePasswordRequest changePasswordRequest,
      HttpServletRequest request) {
    String accessToken = getAccessToken(request);
    authService.changePassword(changePasswordRequest, accessToken);
    return ApiResponse.<Void>builder().code(200).message("Change password successfully").build();
  }


  @PostMapping("/logout")
  ApiResponse<Void> logout(HttpServletRequest request) {
    String accessToken = getAccessToken(request);
    tokenBlacklistService.moveTokenToBlacklist(accessToken);

    return ApiResponse.<Void>builder().code(200).message("Logout successfully").build();
  }

  private String getAccessToken(HttpServletRequest request) {
    return request.getHeader("Authorization").substring(7);
  }


}
