package com.vdt.student_management.academic.service;

import com.vdt.student_management.academic.dto.request.AddStudentClassRequest;
import com.vdt.student_management.academic.dto.response.StudentClassResponse;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface StudentClassService {

  StudentClassResponse upsertStudentClass(Long id, AddStudentClassRequest request);

  StudentClassResponse getStudentClassById(Long id);

  Page<StudentClassResponse> getAllStudentClasses(Pageable pageable);

  void deleteStudentClassById(Long id);


}
