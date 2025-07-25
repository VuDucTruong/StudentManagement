package com.vdt.student_management.academic.service.impl;

import com.vdt.student_management.academic.dto.request.AddSemesterRequest;
import com.vdt.student_management.academic.dto.response.SemesterResponse;
import com.vdt.student_management.academic.mapper.SemesterMapper;
import com.vdt.student_management.academic.repository.SemesterRepository;
import com.vdt.student_management.academic.service.SemesterService;
import com.vdt.student_management.common.enums.ErrorCode;
import com.vdt.student_management.common.exception.AppException;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class SemesterServiceImpl implements SemesterService {

  SemesterRepository semesterRepository;
  SemesterMapper semesterMapper;


  @Override
  public SemesterResponse upsertSemester(Long id, AddSemesterRequest addSemesterRequest) {
    var semester = semesterMapper.toSemester(addSemesterRequest);

    if (id != null) {
      semester.setId(id);
      semesterRepository.findById(id).ifPresentOrElse(s -> {
        if (s.getDeletedAt() != null) {
          throw new AppException(ErrorCode.SEMESTER_NOT_FOUND);
        }
      }, () -> {
        throw new AppException(ErrorCode.SEMESTER_NOT_FOUND);
      });
    }

    return semesterMapper.toSemesterResponse(semesterRepository.save(semester));
  }

  @Override
  public void deleteSemester(Long id) {
    semesterRepository.deleteById(id);
  }

  @Override
  public List<SemesterResponse> getAllSemesters() {
    return semesterRepository.findAll().stream().map(semesterMapper::toSemesterResponse).collect(
        Collectors.toList());
  }

  @Override
  public SemesterResponse getSemester(Long id) {
    return semesterMapper.toSemesterResponse(semesterRepository.findById(id)
        .orElseThrow(() -> new AppException(ErrorCode.SEMESTER_NOT_FOUND)));
  }
}
