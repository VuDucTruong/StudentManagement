package com.vdt.student_management.tuition.mapper;

import com.vdt.student_management.tuition.dto.response.PaymentResponse;
import com.vdt.student_management.tuition.model.Payment;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PaymentMapper {

    PaymentResponse toPaymentResponse(Payment payment);
}
