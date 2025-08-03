package com.vdt.student_management.account.dto.request;

import com.vdt.student_management.common.enums.RoleType;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.util.Set;
import org.springframework.web.multipart.MultipartFile;

public record AddAccountRequest(
    @Size(min = 6, max = 40, message = "INVALID_USERNAME")
    String username,

    @Pattern(regexp = "(?=.*[a-zA-Z])(?=.*[^a-zA-Z0-9\\s])[\\S]{6,40}", message = "INVALID_PASSWORD")
    String password,
    @NotNull(message = "USER_ID_REQUIRED")
    Long linkedId,
    @NotEmpty(message = "INVALID_EMPTY_ROLES")
    Set<RoleType> roles,
    MultipartFile avatar
) {

}
