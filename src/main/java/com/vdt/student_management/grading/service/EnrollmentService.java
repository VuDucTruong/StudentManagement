package com.vdt.student_management.grading.service;


import com.vdt.student_management.grading.dto.request.AddEnrollmentRequest;
import com.vdt.student_management.grading.dto.response.EnrollmentResponse;
import java.util.List;

public interface EnrollmentService {
  EnrollmentResponse addEnrollment(AddEnrollmentRequest addEnrollmentRequest);
  void deleteEnrollment(Long enrollmentId);
  List<EnrollmentResponse> getStudentEnrollments(Long studentId);
}
