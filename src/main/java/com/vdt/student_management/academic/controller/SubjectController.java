package com.vdt.student_management.academic.controller;

import com.vdt.student_management.academic.dto.request.AddSubjectRequest;
import com.vdt.student_management.academic.dto.response.SubjectResponse;
import com.vdt.student_management.academic.service.SubjectService;
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
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/subjects")
@RequiredArgsConstructor
@Tag(name = "Subject", description = "Operations related to subjects")
public class SubjectController {

  SubjectService subjectService;

  @GetMapping
  ResponseEntity<ApiResponse<PageResponse<SubjectResponse>>> getAllSubjects(Pageable pageable) {
    var response = ApiResponse.<PageResponse<SubjectResponse>>builder().code(200)
        .data(PageResponse.fromPage(subjectService.getAllSubjects(pageable))).build();
    return ResponseEntity.ok(response);
  }

  @GetMapping("/{id}")
  ResponseEntity<ApiResponse<SubjectResponse>> getSubject(@PathVariable Long id) {
    var response = ApiResponse.<SubjectResponse>builder().code(200)
        .data(subjectService.getSubjectById(id)).build();

    return ResponseEntity.ok(response);
  }


  @PostMapping
  @PreAuthorize("hasRole(T(com.vdt.student_management.common.enums.RoleType).ADMIN)")
  ResponseEntity<ApiResponse<SubjectResponse>> addSubject(@RequestBody AddSubjectRequest request) {
    var response = ApiResponse.<SubjectResponse>builder().code(201)
        .data(subjectService.upsertSubject(null, request)).build();

    return ResponseEntity.status(HttpStatus.CREATED).body(response);
  }

  @PostMapping("/recover/{id}")
  @PreAuthorize("hasRole(T(com.vdt.student_management.common.enums.RoleType).ADMIN)")
  ResponseEntity<ApiResponse<Void>> recoverSubject(@PathVariable Long id) {
    subjectService.recoverSubjectById(id);

    return ResponseEntity.ok(
        ApiResponse.<Void>builder().code(200).message("Recover successfully").build());
  }

  @PutMapping("/{id}")
  @PreAuthorize("hasRole(T(com.vdt.student_management.common.enums.RoleType).ADMIN)")
  ResponseEntity<ApiResponse<SubjectResponse>> updateSubject(@PathVariable Long id,
      @RequestBody AddSubjectRequest request) {
    var response = ApiResponse.<SubjectResponse>builder().code(200)
        .data(subjectService.upsertSubject(id, request)).build();

    return ResponseEntity.ok(response);
  }

  @DeleteMapping("/{id}")
  @PreAuthorize("hasRole(T(com.vdt.student_management.common.enums.RoleType).ADMIN)")
  ResponseEntity<ApiResponse<Void>> deleteSubject(@PathVariable Long id) {
    subjectService.deleteSubject(id);

    return ResponseEntity.ok(
        ApiResponse.<Void>builder().code(200).message("Delete successfully").build());
  }
}
