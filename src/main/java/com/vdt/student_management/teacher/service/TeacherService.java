package com.vdt.student_management.teacher.service;

import com.vdt.student_management.teacher.dto.TeacherDetailResponse;
import com.vdt.student_management.teacher.dto.TeacherResponse;
import com.vdt.student_management.teacher.model.Teacher;
import java.util.List;

public interface TeacherService {
  void upsertTeacher(Teacher teacher);
  void deleteTeacher(Long id);
  List<TeacherResponse> getAllTeachers();
  TeacherDetailResponse getTeacherById(Long id);
  void recoverTeacher(Long id);
}
