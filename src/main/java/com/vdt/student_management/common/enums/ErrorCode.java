package com.vdt.student_management.common.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum ErrorCode {
  UNKNOWN_ERROR(-1, "Unknown error has occurred", HttpStatus.INTERNAL_SERVER_ERROR),
  RESOURCE_NOT_FOUND(404, "Resource not found", HttpStatus.NOT_FOUND),
  TEACHER_RECOVER_FAILED(4041, "Teacher not found or already active", HttpStatus.NOT_FOUND)
  ;
  int code;
  String message;
  HttpStatusCode httpStatusCode;
}
