package com.vdt.student_management.account.service.impl;

import com.vdt.student_management.academic.repository.StudentRepository;
import com.vdt.student_management.academic.repository.TeacherRepository;
import com.vdt.student_management.account.dto.request.AddAccountRequest;
import com.vdt.student_management.account.dto.request.ChangePasswordRequest;
import com.vdt.student_management.account.dto.request.LoginRequest;
import com.vdt.student_management.account.dto.response.AccountResponse;
import com.vdt.student_management.account.mapper.AccountMapper;
import com.vdt.student_management.account.model.Account;
import com.vdt.student_management.account.repository.AccountRepository;
import com.vdt.student_management.account.service.AccountService;
import com.vdt.student_management.common.enums.ErrorCode;
import com.vdt.student_management.common.exception.AppException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal=true)
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
  AccountRepository accountRepository;
  StudentRepository studentRepository;
  TeacherRepository teacherRepository;
  AccountMapper accountMapper;

  @Override
  public AccountResponse addAccount(AddAccountRequest addAccountRequest) {
    var account = accountMapper.toAccount(addAccountRequest);

    getUserByAccount(account);

    return accountMapper.toAccountResponse(accountRepository.save(account));
  }

  @Override
  public AccountResponse login(LoginRequest loginRequest) {
    var account = accountRepository.findByUsername(loginRequest.username());

    if(account.getPassword().equals(loginRequest.password())) {
      return accountMapper.toAccountResponse(account);
    }

    throw new AppException(ErrorCode.UNAUTHORIZED);

  }
  @Override
  public void deleteAccount(Long id) {
    accountRepository.findById(id).ifPresentOrElse(account -> {
      if(account.getDeletedAt() != null) {
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
      if(account.getDeletedAt() != null) {
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
  public List<AccountResponse> getAllAccounts() {

    return accountRepository.findAll().stream().map(accountMapper::toAccountResponse).collect(Collectors.toList());
  }

  @Override
  public void changePassword(ChangePasswordRequest changePasswordRequest) {

  }


  private void getUserByAccount(Account account) {
    Long id = account.getLinkedId();

    switch (account.getRole()) {
      case STUDENT -> studentRepository.findById(id)
          .orElseThrow(() -> new AppException(ErrorCode.STUDENT_NOT_FOUND));
      case TEACHER -> teacherRepository.findById(id)
          .orElseThrow(() -> new AppException(ErrorCode.TEACHER_NOT_FOUND));
      default -> throw new AppException(ErrorCode.UNKNOWN_ROLE);
    }
  }
}
