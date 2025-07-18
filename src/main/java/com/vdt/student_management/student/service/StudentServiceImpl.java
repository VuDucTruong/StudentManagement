package com.vdt.student_management.student.service;

import com.vdt.student_management.common.enums.ErrorCode;
import com.vdt.student_management.common.exception.AppException;
import com.vdt.student_management.student.dto.response.StudentResponse;
import com.vdt.student_management.student.mapper.StudentMapper;
import com.vdt.student_management.student.model.Student;
import com.vdt.student_management.student.repository.StudentRepository;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class StudentServiceImpl implements StudentService {
 StudentRepository studentRepository;
 StudentMapper studentMapper;

  @Override
  public void upsertStudent(Student student) {
    student.setUpdatedAt(LocalDateTime.now());
    studentRepository.save(student);

  }

  @Override
  public void deleteStudent(Long id) {
    studentRepository.findById(id).ifPresentOrElse(student -> {
      if(student.getDeletedAt() == null) {
        student.setDeletedAt(LocalDateTime.now());
        studentRepository.save(student);
      } else {
        studentRepository.deleteById(id);
      }
    }, () -> {
      throw new AppException(ErrorCode.RESOURCE_NOT_FOUND);
    });

  }


  @Override
  public StudentResponse getStudent(Long id) {
    var student = studentRepository.findById(id).orElseThrow(()->new AppException(ErrorCode.RESOURCE_NOT_FOUND));

    return studentMapper.toStudentResponse(student);
  }

  @Override
  public List<StudentResponse> getStudents() {
    var students = studentRepository.findAll();
    return students.stream().map(studentMapper::toStudentResponse).toList();
  }


  @Override
  public void recoverStudent(Long id) {
    var student = studentRepository.findById(id).orElseThrow(()->new AppException(ErrorCode.RESOURCE_NOT_FOUND));
    student.setDeletedAt(null);
    studentRepository.save(student);
  }
}
