package com.vdt.student_management.academic.service;

import com.vdt.student_management.academic.dto.request.AddClassSectionRequest;
import com.vdt.student_management.academic.dto.response.ClassSectionResponse;
import com.vdt.student_management.academic.model.ClassSection;
import java.util.List;

public interface ClassSectionService {

  ClassSectionResponse upsertClassSection(Long id, AddClassSectionRequest addClassSectionRequest);
  void deleteClassSectionById(Long id);
  ClassSectionResponse getClassSectionById(Long id);
  List<ClassSectionResponse> getAllClassSections();
  void recoverClassSectionById(Long id);

}
