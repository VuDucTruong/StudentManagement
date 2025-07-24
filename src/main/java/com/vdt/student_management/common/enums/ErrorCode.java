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
  CLASS_SECTION_NOT_FOUND(404, "Class section not found", HttpStatus.NOT_FOUND),
  STUDENT_NOT_FOUND(404, "Student not found", HttpStatus.NOT_FOUND),
  ENROLLMENT_NOT_FOUND(404, "This class section hasn't been registered by the student yet", HttpStatus.NOT_FOUND),
  SCORE_NOT_FOUND(404, "Score not found", HttpStatus.NOT_FOUND),
  TEACHER_RECOVER_FAILED(404, "Teacher not found or already active", HttpStatus.NOT_FOUND),
  SUBJECT_RECOVER_FAILED(404, "Subject not found or already active", HttpStatus.NOT_FOUND),
  CLASS_SECTION_RECOVER_FAILED(404, "Class section not found or already active", HttpStatus.NOT_FOUND),
  CANT_UPDATE_DELETED_RESOURCE(4220 , "Can't update deleted resource", HttpStatus.UNPROCESSABLE_ENTITY),

  CANT_ADD_SCORE(5001, "Can't add score for an enrollment", HttpStatus.INTERNAL_SERVER_ERROR),
  ;
  int code;
  String message;
  HttpStatusCode httpStatusCode;
}
