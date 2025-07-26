package com.vdt.student_management.account.dto.response;
import com.vdt.student_management.account.enums.RoleEnum;
import java.time.LocalDateTime;

public record AccountResponse(
    Long id,
    LocalDateTime updatedAt,
    LocalDateTime deletedAt,
    RoleEnum role,
    Object user,
    Token token
) {

}
