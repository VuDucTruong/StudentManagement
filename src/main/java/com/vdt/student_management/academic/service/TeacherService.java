package com.vdt.student_management.academic.service;

import com.vdt.student_management.academic.dto.request.AddTeacherRequest;
import com.vdt.student_management.academic.dto.response.TeacherDetailResponse;
import com.vdt.student_management.academic.dto.response.TeacherResponse;
import com.vdt.student_management.academic.model.Teacher;
import java.util.List;

public interface TeacherService {
  TeacherDetailResponse upsertTeacher(Long id, AddTeacherRequest addTeacherRequest);
  void deleteTeacher(Long id);
  List<TeacherResponse> getAllTeachers();
  TeacherDetailResponse getTeacherById(Long id);
  void recoverTeacher(Long id);
}
