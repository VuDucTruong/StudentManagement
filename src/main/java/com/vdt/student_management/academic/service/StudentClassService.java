package com.vdt.student_management.academic.service;

import com.vdt.student_management.academic.dto.request.AddStudentClassRequest;
import com.vdt.student_management.academic.dto.response.StudentClassResponse;
import java.util.List;

public interface StudentClassService {

  StudentClassResponse upsertStudentClass(Long id, AddStudentClassRequest request);

  StudentClassResponse getStudentClassById(Long id);

  List<StudentClassResponse> getAllStudentClasses();

  void deleteStudentClassById(Long id);


}
