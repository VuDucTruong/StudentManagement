package com.vdt.student_management.academic.service;

import com.vdt.student_management.academic.dto.request.AddSubjectRequest;
import com.vdt.student_management.academic.dto.response.SubjectResponse;
import com.vdt.student_management.academic.model.Subject;
import java.util.List;

public interface SubjectService {

  SubjectResponse upsertSubject(Long id, AddSubjectRequest request);
  void deleteSubject(Long id);
  List<SubjectResponse> getAllSubjects();
  SubjectResponse getSubjectById(Long id);
  void recoverSubjectById(Long id);
}
