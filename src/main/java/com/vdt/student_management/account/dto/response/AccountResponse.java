package com.vdt.student_management.account.dto.response;
import com.vdt.student_management.common.enums.RoleType;
import java.time.LocalDateTime;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountResponse {
  Long id;
  LocalDateTime updatedAt;
  LocalDateTime deletedAt;
  Set<RoleType> roles;
  Object user;
  Token token;
}
