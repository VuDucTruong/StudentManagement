package com.vdt.student_management.tuition.service.impl;

import com.vdt.student_management.academic.repository.ProgramRepository;
import com.vdt.student_management.common.enums.ErrorCode;
import com.vdt.student_management.common.exception.AppException;
import com.vdt.student_management.tuition.dto.request.TuitionFeeRequest;
import com.vdt.student_management.tuition.dto.response.TuitionFeeResponse;
import com.vdt.student_management.tuition.mapper.TuitionFeeMapper;
import com.vdt.student_management.tuition.repository.TuitionFeeRepository;
import com.vdt.student_management.tuition.service.TuitionFeeService;
import java.time.LocalDate;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class TuitionFeeServiceImpl implements TuitionFeeService {

  TuitionFeeRepository tuitionFeeRepository;
  ProgramRepository programRepository;
  TuitionFeeMapper tuitionFeeMapper;

  @Override
  public Page<TuitionFeeResponse> getAllTuitionFees(Pageable pageable) {
    return tuitionFeeRepository.findAll(pageable).map(tuitionFeeMapper::toTuitionFeeResponse);
  }

  @Override
  public TuitionFeeResponse getTuitionFeeById(Long id) {
    return tuitionFeeRepository.findById(id).map(tuitionFeeMapper::toTuitionFeeResponse)
        .orElseThrow(() -> new AppException(
            ErrorCode.TUITION_FEE_NOT_FOUND));
  }

  @Override
  public TuitionFeeResponse addTuitionFee(TuitionFeeRequest tuitionFeeRequest) {
    var fee = tuitionFeeMapper.toTuitionFee(tuitionFeeRequest);

    var program = programRepository.findById(tuitionFeeRequest.programId())
        .orElseThrow(() -> new AppException(ErrorCode.PROGRAM_NOT_FOUND));

    fee.setProgram(program);

    return tuitionFeeMapper.toTuitionFeeResponse(tuitionFeeRepository.save(fee));


  }

  @Override
  public TuitionFeeResponse updateTuitionFee(Long id, TuitionFeeRequest tuitionFeeRequest) {
    var fee = tuitionFeeMapper.toTuitionFee(tuitionFeeRequest);

    if (!tuitionFeeRepository.existsById(id)) {
      throw new AppException(ErrorCode.TUITION_FEE_NOT_FOUND);
    }

    var program = programRepository.findById(tuitionFeeRequest.programId())
        .orElseThrow(() -> new AppException(ErrorCode.PROGRAM_NOT_FOUND));

    fee.setProgram(program);
    fee.setId(id);

    return tuitionFeeMapper.toTuitionFeeResponse(tuitionFeeRepository.save(fee));
  }

  @Override
  public void deleteTuitionFee(Long id) {
    tuitionFeeRepository.deleteById(id);
  }

  @Override
  public TuitionFeeResponse getActiveTuitionFee(Integer academicYear, Long programId) {
    var fee = tuitionFeeRepository.findValidTuitionFee(academicYear, programId, LocalDate.now())
        .orElseThrow(() -> new AppException(ErrorCode.TUITION_FEE_NOT_FOUND));
    return tuitionFeeMapper.toTuitionFeeResponse(fee);
  }
}
