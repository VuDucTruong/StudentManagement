package com.vdt.student_management.common.exception;

import com.vdt.student_management.common.enums.ErrorCode;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class AppException extends RuntimeException {
    ErrorCode errorCode;
}
