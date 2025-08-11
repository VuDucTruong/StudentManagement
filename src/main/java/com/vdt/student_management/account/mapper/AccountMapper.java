package com.vdt.student_management.account.mapper;

import com.vdt.student_management.account.dto.request.AddAccountRequest;
import com.vdt.student_management.account.dto.response.AccountResponse;
import com.vdt.student_management.account.model.Account;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AccountMapper {

    @Mapping(target = "avatarUrl", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    Account toAccount(AddAccountRequest request);

    @Mapping(target = "user", ignore = true)
    @Mapping(target = "token", ignore = true)
    AccountResponse toAccountResponse(Account account);
}
