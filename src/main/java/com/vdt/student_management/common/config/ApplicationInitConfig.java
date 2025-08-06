package com.vdt.student_management.common.config;

import com.vdt.student_management.academic.model.Student;
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
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ApplicationInitConfig {

  PasswordEncoder passwordEncoder;

  @Bean
  ApplicationRunner runner(AccountRepository accountRepository) {
    return args -> {
      if(accountRepository.findByUsername("user01").isEmpty()) {
        Account account = new Account();
        account.setPassword((passwordEncoder.encode("123@abc")));
        account.setUsername("user01");
        account.setRoles(Set.of(RoleType.STUDENT, RoleType.TEACHER, RoleType.ADMIN));
        accountRepository.save(account);
      }
    };
  }
}
