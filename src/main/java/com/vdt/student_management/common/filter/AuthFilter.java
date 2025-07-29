package com.vdt.student_management.common.filter;

import com.vdt.student_management.common.enums.ErrorCode;
import com.vdt.student_management.common.exception.AppException;
import com.vdt.student_management.common.utils.JwtHelper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@RequiredArgsConstructor
@Component
public class AuthFilter extends OncePerRequestFilter {

  JwtHelper jwtHelper;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
      FilterChain filterChain) throws ServletException, IOException {
      String authHeader = request.getHeader("Authorization");

      if(authHeader != null && authHeader.startsWith("Bearer ")) {
        String accessToken = authHeader.substring(7);

        if(!jwtHelper.isTokenValid(accessToken)) {
          throw new AppException(ErrorCode.UNAUTHENTICATED);
        }
      }

      filterChain.doFilter(request, response);
  }
}
