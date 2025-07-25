package com.vdt.student_management.academic.mapper;

import com.vdt.student_management.academic.dto.request.AddSemesterRequest;
import com.vdt.student_management.academic.dto.response.SemesterResponse;
import com.vdt.student_management.academic.model.Semester;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SemesterMapper {

  @Mapping(target = "updatedAt", ignore = true)
  @Mapping(target = "id", ignore = true)
  @Mapping(target = "deletedAt", ignore = true)
  Semester toSemester(AddSemesterRequest request);

  SemesterResponse toSemesterResponse(Semester semester);
}
