package com.vdt.student_management.grading.service;

import com.vdt.student_management.grading.dto.request.AddEnrollmentRequest;
import com.vdt.student_management.grading.dto.response.EnrollmentResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EnrollmentService {

    EnrollmentResponse addEnrollment(AddEnrollmentRequest addEnrollmentRequest);

    void deleteEnrollment(Long enrollmentId);

    Page<EnrollmentResponse> getStudentEnrollments(Long studentId, Pageable pageable);
}
