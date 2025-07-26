package com.vdt.student_management.academic.controller;

import com.vdt.student_management.academic.dto.request.AddFacultyRequest;
import com.vdt.student_management.academic.dto.response.FacultyDetailResponse;
import com.vdt.student_management.academic.dto.response.FacultyResponse;
import com.vdt.student_management.academic.service.FacultyService;
import com.vdt.student_management.common.dto.ApiResponse;
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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/faculties")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Tag(name = "Faculties", description = "Operations related to faculties")
public class FacultyController {

  FacultyService facultyService;


  @GetMapping
  ResponseEntity<ApiResponse<List<FacultyResponse>>> getAllFaculties() {
    return ResponseEntity.ok(
        ApiResponse.<List<FacultyResponse>>builder().code(200).data(facultyService.getAllFaculty())
            .build());
  }

  @GetMapping("/{id}")
  ResponseEntity<ApiResponse<FacultyDetailResponse>> getFacultyById(@PathVariable("id") Long id) {
    return ResponseEntity.ok(ApiResponse.<FacultyDetailResponse>builder().code(200)
        .data(facultyService.getFacultyById(id)).build());
  }

  @PostMapping
  ResponseEntity<ApiResponse<FacultyDetailResponse>> addFaculty(
      @RequestBody AddFacultyRequest request) {

    return ResponseEntity.ok(ApiResponse.<FacultyDetailResponse>builder().code(201)
        .data(facultyService.upsertFaculty(null, request)).build());
  }

  @PostMapping("/recover/{id}")
  ResponseEntity<ApiResponse<Void>> recoverFaculty(@PathVariable("id") Long id) {
    facultyService.recoverFacultyById(id);

    return ResponseEntity.ok(
        ApiResponse.<Void>builder().code(200).message("Recover successfully").build());
  }

  @PutMapping("/{id}")
  ResponseEntity<ApiResponse<FacultyDetailResponse>> updateFaculty(@PathVariable("id") Long id,
      @RequestBody AddFacultyRequest request) {
    return ResponseEntity.ok(ApiResponse.<FacultyDetailResponse>builder().code(200)
        .data(facultyService.upsertFaculty(id, request)).build());
  }

  @DeleteMapping("/{id}")
  ResponseEntity<ApiResponse<Void>> deleteFaculty(@PathVariable("id") Long id) {
    facultyService.deleteFacultyById(id);
    return ResponseEntity.ok(
        ApiResponse.<Void>builder().code(200).message("Delete successfully").build());
  }
}
