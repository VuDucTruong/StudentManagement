package com.vdt.student_management.account.service.impl;

import com.vdt.student_management.account.dto.request.ChangePasswordRequest;
import com.vdt.student_management.account.dto.request.LoginRequest;
import com.vdt.student_management.account.dto.response.AccountResponse;
import com.vdt.student_management.account.dto.response.Token;
import com.vdt.student_management.account.mapper.AccountMapper;
import com.vdt.student_management.account.model.Account;
import com.vdt.student_management.account.repository.AccountRepository;
import com.vdt.student_management.account.repository.InvalidTokenRepository;
import com.vdt.student_management.account.service.AuthService;
import com.vdt.student_management.common.enums.ErrorCode;
import com.vdt.student_management.common.enums.RoleType;
import com.vdt.student_management.common.exception.AppException;
import com.vdt.student_management.common.utils.JwtHelper;
import java.util.List;
import java.util.Objects;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.GrantedAuthority;
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
    InvalidTokenRepository invalidTokenRepository;

    @Override
    public AccountResponse login(LoginRequest loginRequest) {
        var account = accountRepository
                .findByUsername(loginRequest.username())
                .orElseThrow(() -> new AppException(ErrorCode.LOGIN_FAIL));

        if (!passwordEncoder.matches(loginRequest.password(), account.getPassword())) {
            throw new AppException(ErrorCode.LOGIN_FAIL);
        }

        String accessToken = jwtHelper.generateToken(account, false);
        String refreshToken = jwtHelper.generateToken(account, true);

        AccountResponse accountResponse = accountMapper.toAccountResponse(account);

        accountResponse.setToken(new Token(accessToken, refreshToken));

        return accountResponse;
    }

    @Override
    public void changePassword(ChangePasswordRequest request, String accessToken) {
        String username = jwtHelper.getSubject(accessToken);
        var account = accountRepository
                .findByUsername(username)
                .orElseThrow(() -> new AppException(ErrorCode.UNAUTHENTICATED));
        account.setPassword(passwordEncoder.encode(request.newPassword()));
        accountRepository.save(account);
        logout(accessToken);
    }

    @Override
    public AccountResponse refreshToken(String refreshToken) {
        String tokenType = (String) jwtHelper.getClaimFromToken(refreshToken, "type");
        if (Objects.equals(tokenType, "refresh")) {
            String username = jwtHelper.getSubject(refreshToken);
            Account account = accountRepository
                    .findByUsername(username)
                    .orElseThrow(() -> new AppException(ErrorCode.ACCOUNT_NOT_FOUND));
            AccountResponse accountResponse = accountMapper.toAccountResponse(account);
            accountResponse.setToken(new Token(refreshToken, jwtHelper.generateToken(account, false)));
            return accountResponse;
        }
        throw new AppException(ErrorCode.INVALID_REFRESH_TOKEN);
    }

    @Override
    public AccountResponse getMyAccount(String accessToken) {
        String username = jwtHelper.getSubject(accessToken);
        var account = accountRepository
                .findByUsername(username)
                .orElseThrow(() -> new AppException(ErrorCode.ACCOUNT_NOT_FOUND));
        return accountMapper.toAccountResponse(account);
    }

    @Override
    public boolean hasMinRole(RoleType roleType) {
        var auth = SecurityContextHolder.getContext().getAuthentication();
        List<RoleType> current =
                extractRolesFromAuth(auth.getAuthorities().stream().toList());

        for (var role : current) {
            if (RoleType.compare(role, roleType) >= 0) {
                return true;
            }
        }

        return false;
    }

    @Override
    public void logout(String accessToken) {
        long remainingTime = jwtHelper.getRemainingExpTime(accessToken);
        invalidTokenRepository.saveInvalidToken(accessToken, remainingTime);
    }

    private List<RoleType> extractRolesFromAuth(List<? extends GrantedAuthority> authorities) {
        return authorities.stream()
                .map(GrantedAuthority::getAuthority)
                .map(s -> RoleType.valueOf(s.substring(5))) // loại bỏ "ROLE_"
                .toList();
    }
}
