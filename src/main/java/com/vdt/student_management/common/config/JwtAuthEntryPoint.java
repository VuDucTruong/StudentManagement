package com.vdt.student_management.common.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vdt.student_management.common.dto.ApiResponse;
import com.vdt.student_management.common.enums.ErrorCode;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class JwtAuthEntryPoint implements AuthenticationEntryPoint {

  ObjectMapper objectMapper = new ObjectMapper();

  @Override
  public void commence(HttpServletRequest request, HttpServletResponse response,
      AuthenticationException authException) throws IOException, ServletException {
    ErrorCode errorCode = ErrorCode.UNAUTHENTICATED;

    response.setStatus(errorCode.getHttpStatusCode().value());
    response.setContentType("application/json");

    var apiResponse = ApiResponse.builder().code(errorCode.getCode())
        .message(errorCode.getMessage()).build();

    response.getWriter().write(objectMapper.writeValueAsString(apiResponse));
    response.flushBuffer();
  }
}
