package com.vdt.student_management.subject.service;

import com.vdt.student_management.subject.dto.SubjectResponse;
import com.vdt.student_management.subject.model.Subject;
import java.util.List;

public interface SubjectService {

  SubjectResponse upsertSubject(Subject subject);
  void deleteSubject(Long id);
  List<SubjectResponse> getAllSubjects();
  SubjectResponse getSubjectById(Long id);
  void recoverSubjectById(Long id);
}
