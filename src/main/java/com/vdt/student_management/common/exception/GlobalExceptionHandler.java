package com.vdt.student_management.common.exception;


import com.nimbusds.jose.JOSEException;
import com.vdt.student_management.common.dto.ApiResponse;
import com.vdt.student_management.common.enums.ErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(Exception.class)
  ResponseEntity<ApiResponse<Object>> handleGenericException(Exception ex) {
    ErrorCode errorCode = ErrorCode.UNKNOWN_ERROR;
    log.error(ex.getMessage(), ex);
    var apiResponse = ApiResponse.builder().code(errorCode.getCode()).message(
        errorCode.getMessage()).build();
    return new ResponseEntity<>(apiResponse, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler(AppException.class)
  ResponseEntity<ApiResponse<Object>> handleAppException(AppException ex) {
    var apiResponse = ApiResponse.builder().code(ex.getErrorCode().getCode())
        .message(ex.getErrorCode().getMessage()).build();

    return new ResponseEntity<>(apiResponse, ex.getErrorCode().getHttpStatusCode());
  }


  @ExceptionHandler(JOSEException.class)
  ResponseEntity<ApiResponse<Object>> handleJOSEException(JOSEException ex) {
    var apiResponse = ApiResponse.builder().code(ErrorCode.GENERATE_TOKEN_FAIL.getCode()).message(
        ex.getMessage()).build();

    return new ResponseEntity<>(apiResponse, HttpStatus.INTERNAL_SERVER_ERROR);
  }

}
