package com.vdt.student_management.tuition.dto.request;

public record OnlinePaymentRequest(Long tuitionId, Long amountPaid, String note) {}
