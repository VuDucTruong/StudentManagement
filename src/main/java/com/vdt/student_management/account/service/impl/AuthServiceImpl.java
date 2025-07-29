package com.vdt.student_management.account.service.impl;

import com.vdt.student_management.account.dto.request.ChangePasswordRequest;
import com.vdt.student_management.account.dto.request.LoginRequest;
import com.vdt.student_management.account.dto.response.AccountResponse;
import com.vdt.student_management.account.dto.response.Token;
import com.vdt.student_management.account.mapper.AccountMapper;
import com.vdt.student_management.account.model.Account;
import com.vdt.student_management.account.repository.AccountRepository;
import com.vdt.student_management.account.service.AuthService;
import com.vdt.student_management.common.enums.ErrorCode;
import com.vdt.student_management.common.exception.AppException;
import com.vdt.student_management.common.utils.JwtHelper;
import java.util.Objects;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

  AccountRepository accountRepository;
  AccountMapper accountMapper;
  JwtHelper jwtHelper;
  PasswordEncoder passwordEncoder;


  @Override
  public AccountResponse login(LoginRequest loginRequest) {
    var account = accountRepository.findByUsername(loginRequest.username())
        .orElseThrow(() -> new AppException(ErrorCode.UNAUTHENTICATED));

    if(!passwordEncoder.matches(loginRequest.password(), account.getPassword())) {
      throw new AppException(ErrorCode.UNAUTHENTICATED);
    }

    String accessToken = jwtHelper.generateToken(account, false);
    String refreshToken = jwtHelper.generateToken(account, true);

    AccountResponse accountResponse = accountMapper.toAccountResponse(account);

    accountResponse.setToken(new Token(accessToken, refreshToken));

    return accountResponse;

  }

  @Override
  public void changePassword(ChangePasswordRequest changePasswordRequest) {

  }

  @Override
  public void logout() {

  }

  @Override
  public AccountResponse refreshToken(String refreshToken) {
    String tokenType = (String) jwtHelper.getClaimFromToken(refreshToken, "type");
    if(Objects.equals(tokenType, "refresh")) {
      String username = (String) jwtHelper.getClaimFromToken(refreshToken, "subject");
      Account account = accountRepository.findByUsername(username).orElseThrow(() -> new AppException(ErrorCode.ACCOUNT_NOT_FOUND));
      AccountResponse accountResponse = accountMapper.toAccountResponse(account);
      accountResponse.setToken(new Token(refreshToken, jwtHelper.generateToken(account, false)));
      return accountResponse;
    }
    throw new AppException(ErrorCode.INVALID_REFRESH_TOKEN);

  }


}
