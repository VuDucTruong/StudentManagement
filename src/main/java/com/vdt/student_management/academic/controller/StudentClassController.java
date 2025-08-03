package com.vdt.student_management.academic.controller;


import com.vdt.student_management.academic.dto.request.AddStudentClassRequest;
import com.vdt.student_management.academic.dto.response.StudentClassResponse;
import com.vdt.student_management.academic.service.StudentClassService;
import com.vdt.student_management.common.dto.ApiResponse;
import com.vdt.student_management.common.dto.PageResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/student-classes")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Tag(name = "Student Classes", description = "Operations related to student classes")
public class StudentClassController {

  StudentClassService studentClassService;

  @GetMapping
  ResponseEntity<ApiResponse<PageResponse<StudentClassResponse>>> getAllStudentClasses(
      Pageable pageable) {
    return ResponseEntity.ok(ApiResponse.<PageResponse<StudentClassResponse>>builder().code(200)
        .data(PageResponse.fromPage(studentClassService.getAllStudentClasses(pageable))).build());
  }

  @GetMapping("/{id}")
  ResponseEntity<ApiResponse<StudentClassResponse>> getStudentClassById(@PathVariable Long id) {
    return ResponseEntity.ok(ApiResponse.<StudentClassResponse>builder().code(200)
        .data(studentClassService.getStudentClassById(id)).build());
  }

  @PostMapping
  @PreAuthorize("hasRole(T(com.vdt.student_management.common.enums.RoleType).ADMIN)")
  ResponseEntity<ApiResponse<StudentClassResponse>> addStudentClass(
      @RequestBody AddStudentClassRequest request) {
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(ApiResponse.<StudentClassResponse>builder().code(201)
            .data(studentClassService.upsertStudentClass(null, request)).build());
  }


  @PutMapping("/{id}")
  @PreAuthorize("hasRole(T(com.vdt.student_management.common.enums.RoleType).ADMIN)")
  ResponseEntity<ApiResponse<StudentClassResponse>> updateStudentClass(
      @PathVariable Long id, @RequestBody AddStudentClassRequest request) {
    return ResponseEntity.ok(ApiResponse.<StudentClassResponse>builder().code(200)
        .data(studentClassService.upsertStudentClass(id, request)).build());
  }


  @DeleteMapping("/{id}")
  @PreAuthorize("hasRole(T(com.vdt.student_management.common.enums.RoleType).ADMIN)")
  ResponseEntity<ApiResponse<Void>> deleteStudentClass(@PathVariable Long id) {
    studentClassService.deleteStudentClassById(id);

    return ResponseEntity.ok(
        ApiResponse.<Void>builder().code(200).message("Delete successfully").build());
  }

}
