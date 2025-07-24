package com.vdt.student_management.grading.service.impl;

import com.vdt.student_management.academic.model.ClassSection;
import com.vdt.student_management.academic.model.Student;
import com.vdt.student_management.academic.repository.ClassSectionRepository;
import com.vdt.student_management.academic.repository.StudentRepository;
import com.vdt.student_management.common.enums.ErrorCode;
import com.vdt.student_management.common.exception.AppException;
import com.vdt.student_management.grading.dto.request.AddEnrollmentRequest;
import com.vdt.student_management.grading.dto.response.EnrollmentResponse;
import com.vdt.student_management.grading.enums.EnrollmentStatus;
import com.vdt.student_management.grading.mapper.EnrollmentMapper;
import com.vdt.student_management.grading.model.Enrollment;
import com.vdt.student_management.grading.model.Score;
import com.vdt.student_management.grading.repository.EnrollmentRepository;
import com.vdt.student_management.grading.repository.ScoreRepository;
import com.vdt.student_management.grading.service.EnrollmentService;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class EnrollmentServiceImpl implements EnrollmentService {

  EnrollmentRepository enrollmentRepository;
  StudentRepository studentRepository;
  ClassSectionRepository classSectionRepository;
  ScoreRepository scoreRepository;
  EnrollmentMapper enrollmentMapper;

  @Override
  public EnrollmentResponse addEnrollment(AddEnrollmentRequest request) {


    var student = studentRepository.findById(request.studentId()).orElseThrow(() -> new AppException(ErrorCode.STUDENT_NOT_FOUND));
    var classSection = classSectionRepository.findById(request.classSectionId()).orElseThrow(() -> new AppException(ErrorCode.CLASS_SECTION_NOT_FOUND));

    var enrollment = Enrollment.builder().student(student).classSection(classSection).status(EnrollmentStatus.STUDYING).build();
    var score = new Score();
    score.setEnrollment(enrollment);
    scoreRepository.save(score);

    return enrollmentMapper.toEnrollmentResponse(enrollmentRepository.save(enrollment));
  }

  @Override
  public void deleteEnrollment(Long enrollmentId) {
    var enrollment = enrollmentRepository.findById(enrollmentId)
        .orElseThrow(() -> new AppException(ErrorCode.ENROLLMENT_NOT_FOUND));
    if (enrollment.getDeletedAt() == null) {
      enrollment.setDeletedAt(LocalDateTime.now());
      enrollment.setStatus(EnrollmentStatus.CANCELLED);
      enrollment.getScore().setDeletedAt(LocalDateTime.now());
      enrollmentRepository.save(enrollment);
    } else {
      enrollmentRepository.deleteById(enrollmentId);
    }
  }

  @Override
  public List<EnrollmentResponse> getStudentEnrollments(Long studentId) {
    return enrollmentRepository.findAllByStudent_Id(studentId).stream()
        .map(enrollmentMapper::toEnrollmentResponse).toList();
  }
}
