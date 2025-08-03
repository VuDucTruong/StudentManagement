package com.vdt.student_management.academic.controller;

import com.vdt.student_management.academic.dto.request.AddSemesterRequest;
import com.vdt.student_management.academic.dto.response.SemesterResponse;
import com.vdt.student_management.academic.service.SemesterService;
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
@RequestMapping("/semesters")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Tag(name = "Semesters", description = "Operations related to semesters")
public class SemesterController {

  SemesterService semesterService;

  @GetMapping
  ResponseEntity<ApiResponse<PageResponse<SemesterResponse>>> getAllSemesters(Pageable pageable) {
    return ResponseEntity.ok(
        ApiResponse.<PageResponse<SemesterResponse>>builder().code(200)
            .data(PageResponse.fromPage(semesterService.getAllSemesters(pageable)))
            .build());
  }

  @GetMapping("/{id}")
  ResponseEntity<ApiResponse<SemesterResponse>> getSemesterById(@PathVariable Long id) {
    return ResponseEntity.ok(
        ApiResponse.<SemesterResponse>builder().code(200).data(semesterService.getSemester(id))
            .build());
  }

  @PostMapping
  @PreAuthorize("hasRole(T(com.vdt.student_management.common.enums.RoleType).ADMIN)")
  ResponseEntity<ApiResponse<SemesterResponse>> addSemester(
      @RequestBody AddSemesterRequest request) {
    return ResponseEntity.status(HttpStatus.CREATED).body(
        ApiResponse.<SemesterResponse>builder().code(201)
            .data(semesterService.upsertSemester(null, request)).build());
  }

  @PutMapping("/{id}")
  @PreAuthorize("hasRole(T(com.vdt.student_management.common.enums.RoleType).ADMIN)")
  ResponseEntity<ApiResponse<SemesterResponse>> updateSemester(@PathVariable("id") Long id,
      @RequestBody AddSemesterRequest request) {
    return ResponseEntity.status(HttpStatus.OK).body(
        ApiResponse.<SemesterResponse>builder().code(200)
            .data(semesterService.upsertSemester(null, request)).build());
  }


  @DeleteMapping("/{id}")
  @PreAuthorize("hasRole(T(com.vdt.student_management.common.enums.RoleType).ADMIN)")
  ResponseEntity<ApiResponse<Void>> deleteSemester(@PathVariable("id") Long id) {
    semesterService.deleteSemester(id);
    return ResponseEntity.ok(
        ApiResponse.<Void>builder().code(200).message("Delete successfully").build());
  }
}
