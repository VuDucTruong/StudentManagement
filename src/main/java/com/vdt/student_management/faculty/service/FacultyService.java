package com.vdt.student_management.faculty.service;

import com.vdt.student_management.faculty.dto.FacultyDetailResponse;
import com.vdt.student_management.faculty.dto.FacultyResponse;
import com.vdt.student_management.faculty.model.Faculty;
import java.util.List;

public interface FacultyService {
  FacultyDetailResponse upsertFaculty(Faculty faculty);
  FacultyDetailResponse getFacultyById(Long id);
  List<FacultyResponse> getAllFaculty();
  void deleteFacultyById(Long id);
  void recoverFacultyById(Long id);
}
