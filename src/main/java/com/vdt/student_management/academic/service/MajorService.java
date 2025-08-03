package com.vdt.student_management.academic.service;

import com.vdt.student_management.academic.dto.request.AddMajorRequest;
import com.vdt.student_management.academic.dto.response.MajorResponse;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MajorService {
  MajorResponse upsertMajor(Long id, AddMajorRequest request);

  MajorResponse getMajor(Long id);

  Page<MajorResponse> getAllMajor(Pageable pageable);

  void deleteMajor(Long id);

  void recoverMajor(Long id);
}
