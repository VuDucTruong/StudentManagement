package com.vdt.student_management.account.controller;

import com.vdt.student_management.account.dto.request.AddAccountRequest;
import com.vdt.student_management.account.dto.request.ChangePasswordRequest;
import com.vdt.student_management.account.dto.request.LoginRequest;
import com.vdt.student_management.account.dto.response.AccountResponse;
import com.vdt.student_management.account.service.AccountService;
import com.vdt.student_management.common.dto.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/accounts")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Tag(name = "Accounts", description = "Operations related to accounts (authentication)")
public class AccountController {

  AccountService accountService;

  @GetMapping
  ResponseEntity<ApiResponse<List<AccountResponse>>> getAllAccounts() {
    return ResponseEntity.ok(
        ApiResponse.<List<AccountResponse>>builder().code(200).data(accountService.getAllAccounts())
            .build());
  }

  @PostMapping
  ResponseEntity<ApiResponse<AccountResponse>> getAccountById(
      @RequestBody LoginRequest loginRequest) {
    return ResponseEntity.ok(
        ApiResponse.<AccountResponse>builder().code(200).data(accountService.login(loginRequest))
            .build());
  }

  @PostMapping("/add")
  ResponseEntity<ApiResponse<AccountResponse>> addAccount(AddAccountRequest addAccountRequest) {
    return ResponseEntity.status(HttpStatus.CREATED).body(
        ApiResponse.<AccountResponse>builder().code(201)
            .data(accountService.addAccount(addAccountRequest)).build());
  }


  @DeleteMapping("/{id}")
  ResponseEntity<ApiResponse<Void>> deleteAccountById(
      @PathVariable Long id
  ) {
    accountService.deleteAccount(id);
    return ResponseEntity.ok(ApiResponse.<Void>builder().code(200).message("Ban user successfully").build());
  }


  @PostMapping("/{id}")
  ResponseEntity<ApiResponse<Void>> recoverAccountById(@PathVariable Long id) {
    accountService.recoverAccount(id);
    return ResponseEntity.ok(ApiResponse.<Void>builder().code(200).message("Unban user successfully").build());
  }

  @PutMapping("/change-password")
  ResponseEntity<ApiResponse<Void>> changePassword(@RequestBody ChangePasswordRequest changePasswordRequest) {
    accountService.changePassword(changePasswordRequest);

    return ResponseEntity.ok(ApiResponse.<Void>builder().code(200).message("Change password successfully").build());
  }
}
