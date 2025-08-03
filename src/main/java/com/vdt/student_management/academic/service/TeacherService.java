package com.vdt.student_management.academic.service;

import com.vdt.student_management.academic.dto.request.AddTeacherRequest;
import com.vdt.student_management.academic.dto.response.TeacherDetailResponse;
import com.vdt.student_management.academic.dto.response.TeacherResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TeacherService {

  TeacherDetailResponse upsertTeacher(Long id, AddTeacherRequest addTeacherRequest);

  void deleteTeacher(Long id);

  Page<TeacherResponse> getAllTeachers(Pageable pageable);

  TeacherDetailResponse getTeacherById(Long id);

  void recoverTeacher(Long id);
}
