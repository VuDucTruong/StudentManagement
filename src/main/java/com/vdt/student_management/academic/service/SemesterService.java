package com.vdt.student_management.academic.service;


import com.vdt.student_management.academic.dto.request.AddSemesterRequest;
import com.vdt.student_management.academic.dto.response.SemesterResponse;
import java.util.List;

public interface SemesterService {

  SemesterResponse upsertSemester(Long id, AddSemesterRequest addSemesterRequest);

  void deleteSemester(Long id);

  List<SemesterResponse> getAllSemesters();

  SemesterResponse getSemester(Long id);
}
