package com.vdt.student_management.class_section.service;

import com.vdt.student_management.class_section.dto.ClassSectionResponse;
import com.vdt.student_management.class_section.model.ClassSection;
import java.util.List;

public interface ClassSectionService {

  ClassSectionResponse upsertClassSection(ClassSection classSection);
  void deleteClassSectionById(Long id);
  ClassSectionResponse getClassSectionById(Long id);
  List<ClassSectionResponse> getAllClassSections();
  void recoverClassSectionById(Long id);

}
