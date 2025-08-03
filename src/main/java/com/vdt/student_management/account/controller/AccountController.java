package com.vdt.student_management.account.controller;

import com.vdt.student_management.account.dto.request.AddAccountRequest;
import com.vdt.student_management.account.dto.response.AccountResponse;
import com.vdt.student_management.account.service.AccountService;
import com.vdt.student_management.common.dto.ApiResponse;
import com.vdt.student_management.common.dto.PageResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/accounts")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Tag(name = "Accounts", description = "Operations related to accounts (authentication)")
@Slf4j
public class AccountController {

  AccountService accountService;

  @GetMapping
  @PreAuthorize("@authServiceImpl.hasMinRole(T(com.vdt.student_management.common.enums.RoleType).TEACHER)")
  ResponseEntity<ApiResponse<PageResponse<AccountResponse>>> getAllAccounts(Pageable pageable) {
    log.warn(SecurityContextHolder.getContext().getAuthentication().toString());
    return ResponseEntity.ok(
        ApiResponse.<PageResponse<AccountResponse>>builder().code(200)
            .data(PageResponse.fromPage(accountService.getAllAccounts(pageable)))
            .build());
  }

  @PostMapping(value = "/add", consumes = {"multipart/form-data"})
  @PreAuthorize("hasRole(T(com.vdt.student_management.common.enums.RoleType).ADMIN)")
  ResponseEntity<ApiResponse<AccountResponse>> addAccount(
      @ModelAttribute @Valid AddAccountRequest addAccountRequest) {
    return ResponseEntity.status(HttpStatus.CREATED).body(
        ApiResponse.<AccountResponse>builder().code(201)
            .data(accountService.addAccount(addAccountRequest)).build());
  }


  @DeleteMapping("/{id}")
  @PreAuthorize("hasRole(T(com.vdt.student_management.common.enums.RoleType).ADMIN)")
  ResponseEntity<ApiResponse<Void>> deleteAccountById(
      @PathVariable Long id
  ) {
    accountService.deleteAccount(id);
    return ResponseEntity.ok(
        ApiResponse.<Void>builder().code(200).message("Ban user successfully").build());
  }


  @PostMapping("/{id}")
  @PreAuthorize("hasRole(T(com.vdt.student_management.common.enums.RoleType).ADMIN)")
  ResponseEntity<ApiResponse<Void>> recoverAccountById(@PathVariable Long id) {
    accountService.recoverAccount(id);
    return ResponseEntity.ok(
        ApiResponse.<Void>builder().code(200).message("Unban user successfully").build());
  }


}
