package com.vdt.student_management.account.service.impl;

import com.vdt.student_management.academic.repository.StudentRepository;
import com.vdt.student_management.academic.repository.TeacherRepository;
import com.vdt.student_management.account.dto.request.AddAccountRequest;
import com.vdt.student_management.account.dto.response.AccountResponse;
import com.vdt.student_management.account.mapper.AccountMapper;
import com.vdt.student_management.account.repository.AccountRepository;
import com.vdt.student_management.account.service.AccountService;
import com.vdt.student_management.common.enums.ErrorCode;
import com.vdt.student_management.common.exception.AppException;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

  AccountRepository accountRepository;
  AccountMapper accountMapper;
  PasswordEncoder passwordEncoder;

  @Override
  public AccountResponse addAccount(AddAccountRequest addAccountRequest) {
    var account = accountMapper.toAccount(addAccountRequest);

    accountRepository.findByUsername(account.getUsername()).ifPresent(account1 -> {
      throw new AppException(ErrorCode.USERNAME_EXISTS);
    });

    account.setPassword(passwordEncoder.encode(account.getPassword()));
    var linkId = account.getLinkedId();
    if (accountRepository.countByLinkedId(linkId) > 0) {
      throw new AppException(ErrorCode.USER_ALREADY_HAS_ACCOUNT);
    }

    return accountMapper.toAccountResponse(accountRepository.save(account));
  }


  @Override
  public void deleteAccount(Long id) {
    accountRepository.findById(id).ifPresentOrElse(account -> {
      if (account.getDeletedAt() != null) {
        accountRepository.deleteById(id);
      } else {
        account.setDeletedAt(LocalDateTime.now());
        accountRepository.save(account);
      }
    }, () -> {
      throw new AppException(ErrorCode.ACCOUNT_NOT_FOUND);
    });
  }

  @Override
  public void recoverAccount(Long id) {
    accountRepository.findById(id).ifPresentOrElse(account -> {
      if (account.getDeletedAt() != null) {
        account.setDeletedAt(null);
        accountRepository.save(account);
      } else {
        throw new AppException(ErrorCode.ACCOUNT_NOT_FOUND);
      }
    }, () -> {
      throw new AppException(ErrorCode.ACCOUNT_NOT_FOUND);
    });
  }

  @Override
  public Page<AccountResponse> getAllAccounts(Pageable pageable) {

    return accountRepository.findAll(pageable).map(accountMapper::toAccountResponse);
  }


}
