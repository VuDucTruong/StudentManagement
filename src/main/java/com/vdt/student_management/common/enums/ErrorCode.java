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
  FACULTY_NOT_FOUND(404, "Faculty not found", HttpStatus.NOT_FOUND),
  SUBJECT_NOT_FOUND(404, "Subject not found", HttpStatus.NOT_FOUND),
  TEACHER_NOT_FOUND(404, "Teacher not found", HttpStatus.NOT_FOUND),
  MAJOR_NOT_FOUND(404, "Major not found", HttpStatus.NOT_FOUND),
  PROGRAM_NOT_FOUND(404, "Program not found", HttpStatus.NOT_FOUND),
  SCHEDULE_NOT_FOUND(404, "Schedule not found", HttpStatus.NOT_FOUND),
  SEMESTER_NOT_FOUND(404, "Semester not found", HttpStatus.NOT_FOUND),
  ACCOUNT_NOT_FOUND(404, "Account not found", HttpStatus.NOT_FOUND),
  STUDENT_CLASS_NOT_FOUND(404, "Student class not found", HttpStatus.NOT_FOUND),
  PREREQUISITE_NOT_FOUND(404, "Prerequisite not found", HttpStatus.NOT_FOUND),
  TEACHER_RECOVER_FAILED(404, "Teacher not found or already active", HttpStatus.NOT_FOUND),
  SUBJECT_RECOVER_FAILED(404, "Subject not found or already active", HttpStatus.NOT_FOUND),
  PROGRAM_RECOVER_FAILED(404, "Program not found or already active", HttpStatus.NOT_FOUND),
  MAJOR_RECOVER_FAILED(404, "Major not found or already active", HttpStatus.NOT_FOUND),
  CLASS_SECTION_RECOVER_FAILED(404, "Class section not found or already active", HttpStatus.NOT_FOUND),
  CANT_UPDATE_DELETED_RESOURCE(4220 , "Can't update deleted resource", HttpStatus.UNPROCESSABLE_ENTITY),
  UNKNOWN_ROLE(422, "Unknown role", HttpStatus.UNPROCESSABLE_ENTITY),
  USER_ALREADY_HAS_ACCOUNT(400, "This user already has an account", HttpStatus.BAD_REQUEST),
  LOGIN_FAIL(400, "Username or password is wrong", HttpStatus.BAD_REQUEST),
  UNAUTHENTICATED(401, "Unauthenticated, please login!", HttpStatus.UNAUTHORIZED),
  UNAUTHORIZED(401, "You are not authorized to access this resource", HttpStatus.UNAUTHORIZED),
  INVALID_REFRESH_TOKEN(400, "Invalid refresh token", HttpStatus.BAD_REQUEST),

  GENERATE_TOKEN_FAIL(500, "Generate token failed", HttpStatus.INTERNAL_SERVER_ERROR),
  CANT_ADD_SCORE(5001, "Can't add score for an enrollment", HttpStatus.INTERNAL_SERVER_ERROR),
  INVALID_ENUM_KEY(500, "Error code key doesn't exist", HttpStatus.INTERNAL_SERVER_ERROR),
  FILE_UPLOAD_FAIL(500, "Fail to upload file", HttpStatus.INTERNAL_SERVER_ERROR),
  FILE_DELETE_FAIL(500, "Fail to delete file", HttpStatus.INTERNAL_SERVER_ERROR),

  // VALIDATION
  INVALID_USERNAME(400, "Your username must be 6 - 40 characters", HttpStatus.BAD_REQUEST),
  INVALID_PASSWORD(400, "Your password must be 6 - 40 characters and numbers and has at least 1 special character", HttpStatus.BAD_REQUEST),
  INVALID_EMPTY_ROLES(400, "You must add at least one role", HttpStatus.BAD_REQUEST),
  USER_ID_REQUIRED(400, "You have to link with an user", HttpStatus.BAD_REQUEST),
  PASSWORDS_NOT_MATCH(400, "New password and confirm password don't match", HttpStatus.BAD_REQUEST),
  NOT_EMPTY_SECTION_NAME(400, "Class section name can not be empty", HttpStatus.BAD_REQUEST),
  START_DATE_REQUIRED(400, "Start date is required", HttpStatus.BAD_REQUEST),
  END_DATE_REQUIRED(400, "End date is required", HttpStatus.BAD_REQUEST),
  SUBJECT_REQUIRED(400, "Subject is required", HttpStatus.BAD_REQUEST),
  SEMESTER_REQUIRED(400, "Semester is required", HttpStatus.BAD_REQUEST),
  FACULTY_NAME_REQUIRED(400, "Please enter faculty name", HttpStatus.BAD_REQUEST),
  MAJOR_REQUIRED(400, "Please enter major name", HttpStatus.BAD_REQUEST),
  PREREQUISITE_REQUIRED(400, "Prerequisite is required", HttpStatus.BAD_REQUEST),
  PROGRAM_NAME_REQUIRED(400, "Please enter program name", HttpStatus.BAD_REQUEST),
  CLASS_SECTION_REQUIRED(400, "Class section is required", HttpStatus.BAD_REQUEST),
  WEEK_DAY_REQUIRED(400, "Please enter a day of week for class section", HttpStatus.BAD_REQUEST),
  SEMESTER_NAME_REQUIRED(400, "Please enter a semester name", HttpStatus.BAD_REQUEST),
  STUDENT_CLASS_NAME_REQUIRED(400, "Please enter a student class name", HttpStatus.BAD_REQUEST),
  STUDENT_NAME_REQUIRED(400, "Please enter a student name", HttpStatus.BAD_REQUEST),
  SUBJECT_NAME_REQUIRED(400, "Please enter a subject name", HttpStatus.BAD_REQUEST),
  SUBJECT_CODE_REQUIRED(400, "Please enter a subject code", HttpStatus.BAD_REQUEST),
  TEACHER_NAME_REQUIRED(400, "Please enter a teacher name", HttpStatus.BAD_REQUEST),
  FACULTY_REQUIRED(400, "The teacher have to be in a faculty", HttpStatus.BAD_REQUEST),
  STUDENT_CLASS_REQUIRED(400, "The student needs to be in a specific class", HttpStatus.BAD_REQUEST),
  INVALID_SORT_PROPS(400, "Invalid sort properties", HttpStatus.BAD_REQUEST),
  PRECONDITIONS_NOT_MEET(400 , "Preconditions doesn't meet with the current student", HttpStatus.BAD_REQUEST),
  // EXIST
  USERNAME_EXISTS(400, "Username already exists", HttpStatus.BAD_REQUEST),
  ;
  int code;
  String message;
  HttpStatusCode httpStatusCode;
}
