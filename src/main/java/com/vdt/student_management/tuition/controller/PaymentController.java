package com.vdt.student_management.tuition.controller;

import com.vdt.student_management.common.dto.ApiResponse;
import com.vdt.student_management.common.dto.PageResponse;
import com.vdt.student_management.tuition.dto.request.CashPaymentRequest;
import com.vdt.student_management.tuition.dto.request.OnlinePaymentRequest;
import com.vdt.student_management.tuition.dto.response.PaymentResponse;
import com.vdt.student_management.tuition.service.PaymentService;
import java.util.Map;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payments")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class PaymentController {

  PaymentService paymentService;

  @PostMapping("/cash")
  ApiResponse<PaymentResponse> payByCash(@RequestBody CashPaymentRequest request) {
    return ApiResponse.<PaymentResponse>builder().code(201).data(paymentService.payByCash(request))
        .build();
  }

  @PostMapping("/vnpay/create-url")
  ApiResponse<String> payByVnpay(@RequestBody OnlinePaymentRequest request) {
    return ApiResponse.<String>builder().code(201)
        .data(paymentService.createVnpayPayment(request)).build();
  }

  @GetMapping("/vnpay/callback")
  ApiResponse<String> handleVnpayCallback(@RequestParam Map<String, String> params) {
    boolean isSuccess = paymentService.handleVnpayCallback(params);
    if (isSuccess) {
      return ApiResponse.<String>builder().code(200).data("Payment success").build();
    } else {
      return ApiResponse.<String>builder().code(500).data("Payment failed").build();
    }
  }

  @GetMapping("/history/{tuition_id}")
  ApiResponse<PageResponse<PaymentResponse>> getPaymentHistory(
      @PathVariable("tuition_id") Long tuitionId, Pageable pageable) {
    return ApiResponse.<PageResponse<PaymentResponse>>builder().code(200)
        .data(PageResponse.fromPage(paymentService.getPaymentHistory(tuitionId, pageable))).build();
  }
}
