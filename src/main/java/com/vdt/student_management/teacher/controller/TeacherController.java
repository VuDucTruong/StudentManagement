package com.vdt.student_management.teacher.controller;

import com.vdt.student_management.common.dto.ApiResponse;
import com.vdt.student_management.teacher.dto.AddTeacherRequest;
import com.vdt.student_management.teacher.dto.TeacherDetailResponse;
import com.vdt.student_management.teacher.dto.TeacherResponse;
import com.vdt.student_management.teacher.mapper.TeacherMapper;
import com.vdt.student_management.teacher.service.TeacherService;
import java.util.List;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
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
@FieldDefaults(level = AccessLevel.PRIVATE , makeFinal = true)
@RequiredArgsConstructor()
public class TeacherController {
  TeacherService teacherService;
  TeacherMapper teacherMapper;

  @PostMapping
  ApiResponse<Void> addTeacher(@RequestBody AddTeacherRequest request) {
    teacherService.upsertTeacher(teacherMapper.toTeacher(request));
    return new ApiResponse<>();
  }

  @GetMapping
  ApiResponse<List<TeacherResponse>> getAllTeachers() {
    return ApiResponse.<List<TeacherResponse>>builder()
        .data(teacherService.getAllTeachers())
        .build();
  }

  @GetMapping("/{id}")
  ApiResponse<TeacherDetailResponse> getTeacherById(@PathVariable Long id) {
    return ApiResponse.<TeacherDetailResponse>builder()
        .data(teacherService.getTeacherById(id)).build();
  }

  @DeleteMapping("/{id}")
  ApiResponse<Void> deleteTeacherById(@PathVariable Long id) {
    teacherService.deleteTeacher(id);
    return new ApiResponse<>();
  }

  @PutMapping("/{id}")
  ApiResponse<Void> updateTeacher(@PathVariable Long id, @RequestBody AddTeacherRequest request) {
    var teacher = teacherMapper.toTeacher(request);
    teacher.setId(id);
    teacherService.upsertTeacher(teacher);

    return new ApiResponse<>();
  }

  @PostMapping("/recover/{id}")
  ApiResponse<Void> recoverTeacher(@PathVariable Long id) {
    teacherService.recoverTeacher(id);

    return new ApiResponse<>();
  }
}
