package com.vdt.student_management.account.dto.request;

import com.vdt.student_management.account.enums.RoleEnum;

public record AddAccountRequest(
    String username,
    String password,
    Long linkedId,
    RoleEnum role
) {

}
