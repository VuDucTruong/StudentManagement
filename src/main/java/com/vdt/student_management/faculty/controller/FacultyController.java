package com.vdt.student_management.faculty.controller;

import com.vdt.student_management.common.dto.ApiResponse;
import com.vdt.student_management.faculty.dto.AddFacultyRequest;
import com.vdt.student_management.faculty.dto.FacultyDetailResponse;
import com.vdt.student_management.faculty.dto.FacultyResponse;
import com.vdt.student_management.faculty.mapper.FacultyMapper;
import com.vdt.student_management.faculty.service.FacultyService;
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
public class FacultyController {

  FacultyService facultyService;
  FacultyMapper facultyMapper;


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

    var faculty = facultyMapper.toFaculty(request);

    return ResponseEntity.ok(ApiResponse.<FacultyDetailResponse>builder().code(201)
        .data(facultyService.upsertFaculty(faculty)).build());
  }

  @PostMapping("/recover/{id}")
  ResponseEntity<ApiResponse<Void>> recoverFaculty(@PathVariable("id") Long id) {
    facultyService.recoverFacultyById(id);

    return ResponseEntity.ok(ApiResponse.<Void>builder().code(200).message("Recover successfully").build());
  }

  @PutMapping("/{id}")
  ResponseEntity<ApiResponse<FacultyDetailResponse>> updateFaculty(@PathVariable("id") Long id,@RequestBody AddFacultyRequest request) {
    var faculty = facultyMapper.toFaculty(request);
    faculty.setId(id);

    return ResponseEntity.ok(ApiResponse.<FacultyDetailResponse>builder().code(200).data(facultyService.upsertFaculty(faculty)).build());
  }

  @DeleteMapping("/{id}")
  ResponseEntity<ApiResponse<Void>> deleteFaculty(@PathVariable("id") Long id) {
    facultyService.deleteFacultyById(id);
    return ResponseEntity.ok(ApiResponse.<Void>builder().code(200).message("Delete successfully").build());
  }
}
