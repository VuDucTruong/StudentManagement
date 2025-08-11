package com.vdt.student_management.tuition.dto.response;

import com.vdt.student_management.tuition.enums.PaymentMethod;
import java.time.LocalDate;

public record PaymentResponse(
        Long id,
        Long amountPaid,
        LocalDate paymentDate,
        PaymentMethod paymentMethod,
        String referenceNumber,
        String note) {}
