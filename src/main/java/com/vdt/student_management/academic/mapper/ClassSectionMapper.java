package com.vdt.student_management.academic.mapper;

import com.vdt.student_management.academic.dto.request.AddClassSectionRequest;
import com.vdt.student_management.academic.dto.response.ClassSectionResponse;
import com.vdt.student_management.academic.model.ClassSection;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ClassSectionMapper {

  @Mapping(target = "semester", ignore = true)
  @Mapping(target = "updatedAt", ignore = true)
  @Mapping(target = "teacher", ignore = true)
  @Mapping(target = "subject", ignore = true)
  @Mapping(target = "schedules", ignore = true)
  @Mapping(target = "id", ignore = true)
  @Mapping(target = "enrollments", ignore = true)
  @Mapping(target = "deletedAt", ignore = true)
  ClassSection toClassSection(AddClassSectionRequest request);


  @Mapping(target = "schedule", ignore = true)
  @Mapping(target = "numOfStudents", ignore = true)
  ClassSectionResponse toClassSectionResponse(ClassSection section);
}
