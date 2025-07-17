package com.vdt.student_management.student.service;

import com.vdt.student_management.student.dto.response.StudentResponse;
import com.vdt.student_management.student.model.Student;
import java.util.List;


public interface IStudentService {
  void upsertStudent(Student student);
  void deleteStudent(Long id);
  StudentResponse getStudent(Long id);
  List<StudentResponse> getStudents();
  int deleteStudents(List<Long> ids);
}
