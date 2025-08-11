package com.vdt.student_management.common.validator;

import com.vdt.student_management.account.dto.request.ChangePasswordRequest;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, ChangePasswordRequest> {

    @Override
    public boolean isValid(ChangePasswordRequest request, ConstraintValidatorContext context) {
        if (request == null) {
            return false;
        }
        return request.newPassword().equals(request.confirmPassword());
    }
}
