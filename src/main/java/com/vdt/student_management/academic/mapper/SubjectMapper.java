package com.vdt.student_management.academic.mapper;

import com.vdt.student_management.academic.dto.request.AddSubjectRequest;
import com.vdt.student_management.academic.dto.response.SubjectResponse;
import com.vdt.student_management.academic.model.Subject;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SubjectMapper {

  @Mapping(target = "updatedAt", ignore = true)
  @Mapping(target = "major", ignore = true)
  @Mapping(target = "id", ignore = true)
  @Mapping(target = "deletedAt", ignore = true)
  @Mapping(target = "classSections", ignore = true)
  Subject toSubject(AddSubjectRequest request);

  SubjectResponse toSubjectResponse(Subject subject);
}
