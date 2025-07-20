package com.vdt.student_management.academic.controller;

import com.vdt.student_management.common.dto.ApiResponse;
import com.vdt.student_management.academic.dto.request.AddTeacherRequest;
import com.vdt.student_management.academic.dto.response.TeacherDetailResponse;
import com.vdt.student_management.academic.dto.response.TeacherResponse;
import com.vdt.student_management.academic.mapper.TeacherMapper;
import com.vdt.student_management.academic.service.TeacherService;
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
@RequestMapping("/teachers")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor()
public class TeacherController {

  TeacherService teacherService;
  TeacherMapper teacherMapper;

  @PostMapping
  ResponseEntity<ApiResponse<TeacherDetailResponse>> addTeacher(
      @RequestBody AddTeacherRequest request) {
    var upsertedTeacher = teacherService.upsertTeacher(teacherMapper.toTeacher(request));

    var response = ApiResponse.<TeacherDetailResponse>builder().code(201)
        .data(upsertedTeacher).build();

    return ResponseEntity.status(HttpStatus.CREATED).body(response);
  }

  @GetMapping
  ResponseEntity<ApiResponse<List<TeacherResponse>>> getAllTeachers() {
    var response = ApiResponse.<List<TeacherResponse>>builder()
        .code(200)
        .data(teacherService.getAllTeachers())
        .build();

    return ResponseEntity.ok(response);
  }

  @GetMapping("/{id}")
  ResponseEntity<ApiResponse<TeacherDetailResponse>> getTeacherById(@PathVariable Long id) {
    var response = ApiResponse.<TeacherDetailResponse>builder()
        .code(200)
        .data(teacherService.getTeacherById(id))
        .build();

    return ResponseEntity.ok(response);
  }

  @DeleteMapping("/{id}")
  ResponseEntity<ApiResponse<Void>> deleteTeacherById(@PathVariable Long id) {
    teacherService.deleteTeacher(id);
    return ResponseEntity.ok(
        ApiResponse.<Void>builder().code(200).message("Delete successfully").build());
  }

  @PutMapping("/{id}")
  ResponseEntity<ApiResponse<TeacherDetailResponse>> updateTeacher(@PathVariable Long id,
      @RequestBody AddTeacherRequest request) {
    var teacher = teacherMapper.toTeacher(request);
    teacher.setId(id);

    var upsertedTeacher = teacherService.upsertTeacher(teacher);

    var response = ApiResponse.<TeacherDetailResponse>builder().code(200)
        .data(upsertedTeacher)
        .build();

    return ResponseEntity.ok(response);
  }

  @PostMapping("/recover/{id}")
  ResponseEntity<ApiResponse<Void>> recoverTeacher(@PathVariable Long id) {
    teacherService.recoverTeacher(id);

    return ResponseEntity.ok(
        ApiResponse.<Void>builder().code(200).message("Recover successfully").build());
  }
}
