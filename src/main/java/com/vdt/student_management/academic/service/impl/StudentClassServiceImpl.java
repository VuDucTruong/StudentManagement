package com.vdt.student_management.academic.service.impl;

import com.vdt.student_management.academic.dto.request.AddStudentClassRequest;
import com.vdt.student_management.academic.dto.response.StudentClassResponse;
import com.vdt.student_management.academic.mapper.StudentClassMapper;
import com.vdt.student_management.academic.repository.StudentClassRepository;
import com.vdt.student_management.academic.service.StudentClassService;
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
public class StudentClassServiceImpl implements StudentClassService {

  StudentClassRepository studentClassRepository;
  StudentClassMapper studentClassMapper;


  @Override
  public StudentClassResponse upsertStudentClass(Long id, AddStudentClassRequest request) {
    var studentClass = studentClassMapper.toStudentClass(request);

    if (id != null) {
      studentClass.setId(id);
      studentClassRepository.findById(id).ifPresentOrElse(sc -> {
        if (sc.getDeletedAt() != null) {
          throw new AppException(ErrorCode.STUDENT_CLASS_NOT_FOUND);
        }
      }, () -> {
        throw new AppException(ErrorCode.STUDENT_CLASS_NOT_FOUND);
      });
    }

    return studentClassMapper.toStudentClassResponse(studentClassRepository.save(studentClass));
  }

  @Override
  public StudentClassResponse getStudentClassById(Long id) {
    return studentClassMapper.toStudentClassResponse(studentClassRepository.findById(id)
        .orElseThrow(() -> new AppException(ErrorCode.STUDENT_CLASS_NOT_FOUND)));
  }

  @Override
  public List<StudentClassResponse> getAllStudentClasses() {
    return studentClassRepository.findAll().stream().map(studentClassMapper::toStudentClassResponse).collect(
        Collectors.toList());
  }

  @Override
  public void deleteStudentClassById(Long id) {
    studentClassRepository.deleteById(id);
  }
}
