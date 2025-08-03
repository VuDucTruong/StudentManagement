package com.vdt.student_management.academic.service;

import com.vdt.student_management.academic.dto.request.AddSubjectRequest;
import com.vdt.student_management.academic.dto.response.SubjectResponse;
import com.vdt.student_management.academic.model.Subject;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SubjectService {

  SubjectResponse upsertSubject(Long id, AddSubjectRequest request);
  void deleteSubject(Long id);
  Page<SubjectResponse> getAllSubjects(Pageable pageable);
  SubjectResponse getSubjectById(Long id);
  void recoverSubjectById(Long id);
}
