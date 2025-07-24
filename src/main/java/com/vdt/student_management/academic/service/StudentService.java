package com.vdt.student_management.academic.service;

import com.vdt.student_management.academic.dto.request.AddStudentRequest;
import com.vdt.student_management.academic.dto.response.StudentResponse;
import com.vdt.student_management.academic.model.Student;
import java.util.List;


public interface StudentService {
  StudentResponse upsertStudent(Long id, AddStudentRequest addStudentRequest);
  void deleteStudent(Long id);
  StudentResponse getStudent(Long id);
  List<StudentResponse> getStudents();
  void recoverStudent(Long id);
}
