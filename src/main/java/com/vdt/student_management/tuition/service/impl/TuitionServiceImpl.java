package com.vdt.student_management.tuition.service.impl;

import com.vdt.student_management.common.enums.ErrorCode;
import com.vdt.student_management.common.exception.AppException;
import com.vdt.student_management.tuition.dto.response.TuitionResponse;
import com.vdt.student_management.tuition.mapper.TuitionMapper;
import com.vdt.student_management.tuition.repository.TuitionRepository;
import com.vdt.student_management.tuition.service.TuitionService;
import java.util.List;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;


@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class TuitionServiceImpl implements TuitionService {

  TuitionRepository tuitionRepository;
  TuitionMapper tuitionMapper;

  @Override
  public List<TuitionResponse> getTuitionsByStudent(Long studentId) {
    return tuitionRepository.getByStudentId(studentId).stream()
        .map(tuitionMapper::toTuitionResponse).toList();
  }

  @Override
  public TuitionResponse getTuitionById(Long id) {
    var tuition = tuitionRepository.findById(id)
        .orElseThrow(() -> new AppException(ErrorCode.TUITION_NOT_FOUND));
    return tuitionMapper.toTuitionResponse(tuition);
  }
}
