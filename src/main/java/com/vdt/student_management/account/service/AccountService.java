package com.vdt.student_management.account.service;

import com.vdt.student_management.account.dto.request.AddAccountRequest;
import com.vdt.student_management.account.dto.response.AccountResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AccountService {

  AccountResponse addAccount(AddAccountRequest addAccountRequest);

  void deleteAccount(Long id);

  void recoverAccount(Long id);

  Page<AccountResponse> getAllAccounts(Pageable pageable);

}
