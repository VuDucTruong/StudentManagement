package com.vdt.student_management.account.dto.request;

import com.vdt.student_management.account.enums.RoleType;
import java.util.Set;

public record AddAccountRequest(
    String username,
    String password,
    Long linkedId,
    Set<RoleType> roles
) {

}
