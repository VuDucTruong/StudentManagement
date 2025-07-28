package com.vdt.student_management.account.controller;

import com.vdt.student_management.account.dto.request.ChangePasswordRequest;
import com.vdt.student_management.account.dto.request.LoginRequest;
import com.vdt.student_management.account.dto.response.AccountResponse;
import com.vdt.student_management.account.service.AccountService;
import com.vdt.student_management.common.dto.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
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
public class AuthController {
  AccountService accountService;

  @PostMapping("/login")
  ResponseEntity<ApiResponse<AccountResponse>> login(
      @RequestBody LoginRequest loginRequest) {
    return ResponseEntity.ok(
        ApiResponse.<AccountResponse>builder().code(200).data(accountService.login(loginRequest))
            .build());
  }

  @PutMapping("/change-password")
  ResponseEntity<ApiResponse<Void>> changePassword(@RequestBody ChangePasswordRequest changePasswordRequest) {
    accountService.changePassword(changePasswordRequest);

    return ResponseEntity.ok(ApiResponse.<Void>builder().code(200).message("Change password successfully").build());
  }



}
