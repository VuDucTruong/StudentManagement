package com.vdt.student_management.subject.mapper;

import com.vdt.student_management.subject.dto.AddSubjectRequest;
import com.vdt.student_management.subject.dto.SubjectResponse;
import com.vdt.student_management.subject.model.Subject;
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
