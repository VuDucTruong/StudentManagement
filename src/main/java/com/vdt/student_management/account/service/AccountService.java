package com.vdt.student_management.account.service;

import com.vdt.student_management.account.dto.request.AddAccountRequest;
import com.vdt.student_management.account.dto.request.ChangePasswordRequest;
import com.vdt.student_management.account.dto.request.LoginRequest;
import com.vdt.student_management.account.dto.response.AccountResponse;
import java.util.List;

public interface AccountService {

  AccountResponse addAccount(AddAccountRequest addAccountRequest);

  AccountResponse login(LoginRequest loginRequest);

  void deleteAccount(Long id);

  void recoverAccount(Long id);

  List<AccountResponse> getAllAccounts();

  void changePassword(ChangePasswordRequest changePasswordRequest);
}
