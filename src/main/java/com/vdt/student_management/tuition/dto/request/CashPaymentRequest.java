package com.vdt.student_management.tuition.dto.request;

public record CashPaymentRequest(Long tuitionId, Long amountPaid, String note) {}
