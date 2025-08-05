package com.vdt.student_management.tuition.service;

import com.vdt.student_management.tuition.dto.request.TuitionFeeRequest;
import com.vdt.student_management.tuition.dto.response.TuitionFeeResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TuitionFeeService {
  Page<TuitionFeeResponse> getAllTuitionFees(Pageable pageable);
  TuitionFeeResponse getTuitionFeeById(Long id);
  TuitionFeeResponse addTuitionFee(TuitionFeeRequest tuitionFeeRequest);
  TuitionFeeResponse updateTuitionFee(Long id, TuitionFeeRequest tuitionFeeRequest);
  void deleteTuitionFee(Long id);
  TuitionFeeResponse getActiveTuitionFee(Integer academicYear, Long programId);
}
