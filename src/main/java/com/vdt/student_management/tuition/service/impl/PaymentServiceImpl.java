package com.vdt.student_management.tuition.service.impl;

import com.vdt.student_management.common.enums.ErrorCode;
import com.vdt.student_management.common.exception.AppException;
import com.vdt.student_management.common.utils.VnpayUtils;
import com.vdt.student_management.tuition.dto.request.CashPaymentRequest;
import com.vdt.student_management.tuition.dto.request.OnlinePaymentRequest;
import com.vdt.student_management.tuition.dto.response.PaymentResponse;
import com.vdt.student_management.tuition.enums.PaymentMethod;
import com.vdt.student_management.tuition.mapper.PaymentMapper;
import com.vdt.student_management.tuition.model.Payment;
import com.vdt.student_management.tuition.repository.PaymentRepository;
import com.vdt.student_management.tuition.repository.TuitionRepository;
import com.vdt.student_management.tuition.service.PaymentService;
import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Map;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

  PaymentRepository paymentRepository;
  TuitionRepository tuitionRepository;
  PaymentMapper paymentMapper;
  VnpayUtils vnpayUtils;

  @Override
  public PaymentResponse payByCash(CashPaymentRequest request) {

    var tuition = tuitionRepository.findById(request.tuitionId()).orElseThrow(() -> new AppException(
        ErrorCode.TUITION_NOT_FOUND));

    var cashPayment = Payment.builder().paymentDate(LocalDate.now())
        .paymentMethod(PaymentMethod.ON_CASH).note(request.note()).amountPaid(request.amountPaid())
        .tuition(tuition)
        .build();

    return paymentMapper.toPaymentResponse(paymentRepository.save(cashPayment));
  }

  @Override
  public String createVnpayPayment(OnlinePaymentRequest request) {
    try {
      return vnpayUtils.createPaymentUrl(request.amountPaid(), request.note() , request.tuitionId());
    } catch (UnsupportedEncodingException e) {
      throw new AppException(ErrorCode.GENERATE_VNPAY_URL_FAIL);
    }
  }

  @Override
  public boolean handleVnpayCallback(Map<String, String> params) {
    String responseCode = params.get("vnp_ResponseCode");
    if ("00".equals(responseCode)) {
      String orderInfo = params.get("vnp_OrderInfo");
      Long amountPaid = Long.parseLong(params.get("vnp_Amount"));
      String[] contents = orderInfo.split("/");
      log.warn(Arrays.toString(contents));

      var tuition = tuitionRepository.findById(Long.parseLong(contents[1])).orElseThrow(() -> new AppException(
          ErrorCode.TUITION_NOT_FOUND));

      var cashPayment = Payment.builder().paymentDate(LocalDate.now())
          .paymentMethod(PaymentMethod.ONLINE).amountPaid(amountPaid)
          .tuition(tuition)
          .note(contents[0])
          .build();
      paymentRepository.save(cashPayment);
      return true;
    } else {
      return false;
    }
  }

  @Override
  public Page<PaymentResponse> getPaymentHistory(Long tuitionId, Pageable pageable) {
    return paymentRepository.findByTuitionId(tuitionId, pageable).map(paymentMapper::toPaymentResponse);
  }
}
