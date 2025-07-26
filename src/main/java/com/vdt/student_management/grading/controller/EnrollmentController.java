package com.vdt.student_management.grading.controller;

import com.vdt.student_management.common.dto.ApiResponse;
import com.vdt.student_management.grading.dto.request.AddEnrollmentRequest;
import com.vdt.student_management.grading.dto.response.EnrollmentResponse;
import com.vdt.student_management.grading.service.EnrollmentService;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/enrollments")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Tag(name = "Enrollments", description = "Operations related to enrollments to class sections")
public class EnrollmentController {

  EnrollmentService enrollmentService;


  @GetMapping("/{student_id}")
  ResponseEntity<ApiResponse<List<EnrollmentResponse>>> getEnrollments(
      @PathVariable("student_id") Long studentId) {
    var response = ApiResponse.<List<EnrollmentResponse>>builder().code(200)
        .data(enrollmentService.getStudentEnrollments(studentId)).build();
    return ResponseEntity.ok(response);
  }

  @PostMapping("/enroll")
  ResponseEntity<ApiResponse<EnrollmentResponse>> enrollClassSection(
      @RequestBody AddEnrollmentRequest request) {
    return ResponseEntity.ok(ApiResponse.<EnrollmentResponse>builder().code(201)
        .data(enrollmentService.addEnrollment(request)).build());
  }

  @DeleteMapping("/{id}")
  ResponseEntity<ApiResponse<Void>> deleteEnrollment(@PathVariable("id") Long id) {
    enrollmentService.deleteEnrollment(id);
    return ResponseEntity.ok(
        ApiResponse.<Void>builder().code(200).message("Delete successfully").build());
  }
}
