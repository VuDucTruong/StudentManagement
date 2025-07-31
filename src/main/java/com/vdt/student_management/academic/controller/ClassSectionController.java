package com.vdt.student_management.academic.controller;

import com.vdt.student_management.academic.dto.request.AddClassSectionRequest;
import com.vdt.student_management.academic.dto.response.ClassSectionResponse;
import com.vdt.student_management.academic.mapper.ClassSectionMapper;
import com.vdt.student_management.academic.service.ClassSectionService;
import com.vdt.student_management.common.dto.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
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
@RequestMapping("/class-sections")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Tag(name = "Class sections", description = "Operations related to class sections")
public class ClassSectionController {
  ClassSectionService classSectionService;

  @GetMapping
  ResponseEntity<ApiResponse<List<ClassSectionResponse>>> getAllClassSections() {

    return ResponseEntity.ok(ApiResponse.<List<ClassSectionResponse>>builder().code(200)
        .data(classSectionService.getAllClassSections()).build());
  }

  @GetMapping("/{id}")
  ResponseEntity<ApiResponse<ClassSectionResponse>> getClassSectionById(@PathVariable Long id) {
    return ResponseEntity.ok(ApiResponse.<ClassSectionResponse>builder().code(200)
        .data(classSectionService.getClassSectionById(id)).build());
  }

  @PostMapping
  @PreAuthorize("hasRole(T(com.vdt.student_management.common.enums.RoleType).ADMIN)")
  ResponseEntity<ApiResponse<ClassSectionResponse>> addClassSection(
      @RequestBody AddClassSectionRequest request) {
    return ResponseEntity.status(HttpStatus.CREATED).body(
        ApiResponse.<ClassSectionResponse>builder().code(201)
            .data(classSectionService.upsertClassSection(null,request)).build());
  }

  @PostMapping("/{id}")
  @PreAuthorize("hasRole(T(com.vdt.student_management.common.enums.RoleType).ADMIN)")
  ResponseEntity<ApiResponse<Void>> recoverClassSection(@PathVariable Long id) {
    classSectionService.recoverClassSectionById(id);

    return ResponseEntity.ok(
        ApiResponse.<Void>builder().code(200).message("Recover successfully").build());
  }

  @PutMapping("/{id}")
  @PreAuthorize("hasRole(T(com.vdt.student_management.common.enums.RoleType).ADMIN)")
  ResponseEntity<ApiResponse<ClassSectionResponse>> updateClassSection(@PathVariable Long id,
      @RequestBody AddClassSectionRequest request) {

    return ResponseEntity.ok(ApiResponse.<ClassSectionResponse>builder().code(200)
        .data(classSectionService.upsertClassSection(id , request)).build());
  }

  @DeleteMapping("/{id}")
  @PreAuthorize("hasRole(T(com.vdt.student_management.common.enums.RoleType).ADMIN)")
  ResponseEntity<ApiResponse<Void>> deleteClassSection(@PathVariable Long id) {
    classSectionService.deleteClassSectionById(id);

    return ResponseEntity.ok(ApiResponse.<Void>builder().code(200).message("Delete successfully").build());
  }

}
