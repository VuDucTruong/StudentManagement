package com.vdt.student_management.academic.service;

import com.vdt.student_management.academic.dto.request.AddMajorRequest;
import com.vdt.student_management.academic.dto.response.MajorResponse;
import java.util.List;

public interface MajorService {
  MajorResponse upsertMajor(Long id, AddMajorRequest request);

  MajorResponse getMajor(Long id);

  List<MajorResponse> getAllMajor();

  void deleteMajor(Long id);

  void recoverMajor(Long id);
}
