package com.vdt.student_management.account.repository;

import com.vdt.student_management.account.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {

  Account findByUsername(String username);
}
