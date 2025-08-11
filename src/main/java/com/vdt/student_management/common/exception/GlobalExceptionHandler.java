package com.vdt.student_management.common.exception;

import com.nimbusds.jose.JOSEException;
import com.vdt.student_management.common.dto.ApiResponse;
import com.vdt.student_management.common.enums.ErrorCode;
import jakarta.validation.ConstraintViolation;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.data.mapping.PropertyReferenceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    ResponseEntity<ApiResponse<Object>> handleGenericException(Exception ex) {
        ErrorCode errorCode = ErrorCode.UNKNOWN_ERROR;
        log.error(ex.getMessage(), ex);
        var apiResponse = ApiResponse.builder()
                .code(errorCode.getCode())
                .message(errorCode.getMessage())
                .build();
        return new ResponseEntity<>(apiResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(AppException.class)
    ResponseEntity<ApiResponse<Object>> handleAppException(AppException ex) {
        var apiResponse = ApiResponse.builder()
                .code(ex.getErrorCode().getCode())
                .message(ex.getErrorCode().getMessage())
                .build();

        return new ResponseEntity<>(apiResponse, ex.getErrorCode().getHttpStatusCode());
    }

    @ExceptionHandler(JOSEException.class)
    ResponseEntity<ApiResponse<Object>> handleJOSEException(JOSEException ex) {
        var apiResponse = ApiResponse.builder()
                .code(ErrorCode.GENERATE_TOKEN_FAIL.getCode())
                .message(ex.getMessage())
                .build();

        return new ResponseEntity<>(apiResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(AccessDeniedException.class)
    ResponseEntity<ApiResponse<Object>> handleAccessDeniedException(AccessDeniedException ex) {
        ErrorCode errorCode = ErrorCode.UNAUTHORIZED;
        var apiResponse = ApiResponse.builder()
                .code(errorCode.getCode())
                .message(errorCode.getMessage())
                .build();
        return new ResponseEntity<>(apiResponse, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    ResponseEntity<ApiResponse<Object>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {

        String errorCodeKey = ex.getFieldError().getDefaultMessage();
        ErrorCode errorCode;
        Map<String, Object> attributes = null;
        try {
            errorCode = ErrorCode.valueOf(errorCodeKey);

            var constraints = ex.getAllErrors().getFirst().unwrap(ConstraintViolation.class);

            attributes = constraints.getConstraintDescriptor().getAttributes();

            log.info(attributes.toString());

        } catch (IllegalArgumentException e) {
            errorCode = ErrorCode.INVALID_ENUM_KEY;
        }

        var apiResponse = ApiResponse.builder()
                .code(errorCode.getCode())
                .message(errorCode.getMessage())
                .build();

        return ResponseEntity.badRequest().body(apiResponse);
    }

    @ExceptionHandler(PropertyReferenceException.class)
    ResponseEntity<ApiResponse<Object>> handlePropertyReferenceException(PropertyReferenceException ex) {
        ErrorCode errorCode = ErrorCode.INVALID_SORT_PROPS;

        var apiResponse = ApiResponse.builder()
                .code(errorCode.getCode())
                .message(errorCode.getMessage() + " : " + ex.getPropertyName())
                .build();
        return ResponseEntity.badRequest().body(apiResponse);
    }

    @ExceptionHandler(FileUploadException.class)
    ResponseEntity<ApiResponse<Object>> handleFileUploadException(FileUploadException ex) {
        ErrorCode errorCode = ErrorCode.FILE_UPLOAD_FAIL;

        var apiResponse = ApiResponse.builder()
                .code(errorCode.getCode())
                .message(errorCode.getMessage() + " : " + ex.getMessage())
                .build();
        return ResponseEntity.badRequest().body(apiResponse);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    ResponseEntity<ApiResponse<Object>> handleIllegalArgumentException(IllegalArgumentException ex) {
        ErrorCode errorCode = ErrorCode.INVALID_INPUT;
        var apiResponse = ApiResponse.builder()
                .code(errorCode.getCode())
                .message(errorCode.getMessage() + " : " + ex.getMessage())
                .build();
        return ResponseEntity.badRequest().body(apiResponse);
    }
}
