package com.vdt.student_management.academic.service;

import com.vdt.student_management.academic.dto.request.AddFacultyRequest;
import com.vdt.student_management.academic.dto.response.FacultyDetailResponse;
import com.vdt.student_management.academic.dto.response.FacultyResponse;
import com.vdt.student_management.academic.model.Faculty;
import java.util.List;

public interface FacultyService {
  FacultyDetailResponse upsertFaculty(Long id, AddFacultyRequest addFacultyRequest);
  FacultyDetailResponse getFacultyById(Long id);
  List<FacultyResponse> getAllFaculty();
  void deleteFacultyById(Long id);
  void recoverFacultyById(Long id);
}
