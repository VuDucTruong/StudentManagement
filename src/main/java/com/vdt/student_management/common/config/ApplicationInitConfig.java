package com.vdt.student_management.common.config;

import com.vdt.student_management.common.enums.RoleType;
import com.vdt.student_management.account.model.Account;
import com.vdt.student_management.account.repository.AccountRepository;
import java.util.Set;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ApplicationInitConfig {


  @Bean
  ApplicationRunner runner(AccountRepository accountRepository) {
    return args -> {
      Account account = new Account();
      account.setPassword(("123456"));
      account.setUsername("user1");
      account.setRoles(Set.of(RoleType.STUDENT, RoleType.TEACHER));
      accountRepository.save(account);
    };
  }
}
