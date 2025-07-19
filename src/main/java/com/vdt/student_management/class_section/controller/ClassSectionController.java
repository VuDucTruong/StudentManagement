package com.vdt.student_management.class_section.controller;

import com.vdt.student_management.class_section.dto.AddClassSectionRequest;
import com.vdt.student_management.class_section.dto.ClassSectionResponse;
import com.vdt.student_management.class_section.mapper.ClassSectionMapper;
import com.vdt.student_management.class_section.service.ClassSectionService;
import com.vdt.student_management.common.dto.ApiResponse;
import java.util.List;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
public class ClassSectionController {

  ClassSectionService classSectionService;
  ClassSectionMapper classSectionMapper;

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
  ResponseEntity<ApiResponse<ClassSectionResponse>> addClassSection(
      @RequestBody AddClassSectionRequest request) {
    var classSection = classSectionMapper.toClassSection(request);
    return ResponseEntity.status(HttpStatus.CREATED).body(
        ApiResponse.<ClassSectionResponse>builder().code(201)
            .data(classSectionService.upsertClassSection(classSection)).build());
  }

  @PostMapping("/{id}")
  ResponseEntity<ApiResponse<Void>> recoverClassSection(@PathVariable Long id) {
    classSectionService.recoverClassSectionById(id);

    return ResponseEntity.ok(
        ApiResponse.<Void>builder().code(200).message("Recover successfully").build());
  }

  @PutMapping("/{id}")
  ResponseEntity<ApiResponse<ClassSectionResponse>> updateClassSection(@PathVariable Long id,
      @RequestBody AddClassSectionRequest request) {
    var classSection = classSectionMapper.toClassSection(request);
    classSection.setId(id);

    return ResponseEntity.ok(ApiResponse.<ClassSectionResponse>builder().code(200)
        .data(classSectionService.upsertClassSection(classSection)).build());
  }

  @DeleteMapping("/{id}")
  ResponseEntity<ApiResponse<Void>> deleteClassSection(@PathVariable Long id) {
    classSectionService.deleteClassSectionById(id);

    return ResponseEntity.ok(ApiResponse.<Void>builder().code(200).message("Delete successfully").build());
  }

}
