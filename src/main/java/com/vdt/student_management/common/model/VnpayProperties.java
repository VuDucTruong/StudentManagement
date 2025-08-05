package com.vdt.student_management.common.model;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "vnpay")
public class VnpayProperties {

  String vnpTmnCode;
  String vnpHashSecret;
  String vnpPayUrl;
  String vnpReturnUrl;
}
