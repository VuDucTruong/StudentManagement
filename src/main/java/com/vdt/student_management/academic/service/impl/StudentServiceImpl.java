package com.vdt.student_management.academic.service.impl;

import com.vdt.student_management.academic.dto.request.AddStudentRequest;
import com.vdt.student_management.academic.service.StudentService;
import com.vdt.student_management.common.enums.ErrorCode;
import com.vdt.student_management.common.exception.AppException;
import com.vdt.student_management.academic.dto.response.StudentResponse;
import com.vdt.student_management.academic.mapper.StudentMapper;
import com.vdt.student_management.academic.model.Student;
import com.vdt.student_management.academic.repository.StudentRepository;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class StudentServiceImpl implements StudentService {

  StudentRepository studentRepository;
  StudentMapper studentMapper;

  @Override
  public StudentResponse upsertStudent(Long id, AddStudentRequest request) {
    var student = studentMapper.toStudent(request);

    //if update
    if (id != null) {
      student.setId(id);
      studentRepository.findById(student.getId()).ifPresentOrElse(student1 -> {
        if (student1.getDeletedAt() != null) {
          throw new AppException(ErrorCode.CANT_UPDATE_DELETED_RESOURCE);
        }
      }, () -> {
        throw new AppException(ErrorCode.STUDENT_NOT_FOUND);
      });
    }

    return studentMapper.toStudentResponse(studentRepository.save(student));
  }

  @Override
  public void deleteStudent(Long id) {
    studentRepository.findById(id).ifPresentOrElse(student -> {
      if (student.getDeletedAt() == null) {
        student.setDeletedAt(LocalDateTime.now());
        studentRepository.save(student);
      } else {
        studentRepository.deleteById(id);
      }
    }, () -> {
      throw new AppException(ErrorCode.STUDENT_NOT_FOUND);
    });

  }


  @Override
  public StudentResponse getStudent(Long id) {
    var student = studentRepository.findById(id)
        .orElseThrow(() -> new AppException(ErrorCode.STUDENT_NOT_FOUND));

    return studentMapper.toStudentResponse(student);
  }

  @Override
  public Page<StudentResponse> getStudents(Pageable pageable) {
    var students = studentRepository.findAll(pageable);
    return students.map(studentMapper::toStudentResponse);
  }


  @Override
  public void recoverStudent(Long id) {
    var student = studentRepository.findById(id)
        .orElseThrow(() -> new AppException(ErrorCode.STUDENT_NOT_FOUND));
    student.setDeletedAt(null);
    studentRepository.save(student);
  }
}
