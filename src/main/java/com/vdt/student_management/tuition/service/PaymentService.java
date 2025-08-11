package com.vdt.student_management.tuition.service;

import com.vdt.student_management.tuition.dto.request.CashPaymentRequest;
import com.vdt.student_management.tuition.dto.request.OnlinePaymentRequest;
import com.vdt.student_management.tuition.dto.response.PaymentResponse;
import java.util.Map;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PaymentService {
    PaymentResponse payByCash(CashPaymentRequest request);

    String createVnpayPayment(OnlinePaymentRequest request);

    boolean handleVnpayCallback(Map<String, String> params);

    Page<PaymentResponse> getPaymentHistory(Long tuitionId, Pageable pageable);
}
