package com.vdt.student_management.common.config;

import com.vdt.student_management.common.filter.AuthFilter;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@EnableMethodSecurity
@Configuration
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SecurityConfig {

  AuthFilter authFilter;

  private static final String[] WHITELIST = {
      "/v3/api-docs/**",
      "/swagger-ui/**",
      "/swagger-resources/**",
      "/configuration/**"
  };

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    return http.csrf(AbstractHttpConfigurer::disable).cors(AbstractHttpConfigurer::disable)
        .authorizeHttpRequests(
            auth -> auth.requestMatchers(WHITELIST).permitAll()
                .requestMatchers(HttpMethod.POST, "/auth/**").permitAll().anyRequest()
                .authenticated())
        .addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class)
        .exceptionHandling(exception -> exception.authenticationEntryPoint(new JwtAuthEntryPoint())).build();
  }

  @Bean
  PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
}
