package com.vdt.student_management.faculty.mapper;

import com.vdt.student_management.faculty.dto.AddFacultyRequest;
import com.vdt.student_management.faculty.dto.FacultyDetailResponse;
import com.vdt.student_management.faculty.dto.FacultyResponse;
import com.vdt.student_management.faculty.model.Faculty;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface FacultyMapper {

  @Mapping(target = "updatedAt", ignore = true)
  @Mapping(target = "teachers", ignore = true)
  @Mapping(target = "id", ignore = true)
  @Mapping(target = "deletedAt", ignore = true)
  @Mapping(target = "dean", ignore = true)
  Faculty toFaculty(AddFacultyRequest request);

  FacultyResponse toFacultyResponse(Faculty faculty);

  FacultyDetailResponse toFacultyDetailResponse(Faculty faculty);

}
