package com.vdt.student_management.academic.mapper;

import com.vdt.student_management.academic.dto.request.AddFacultyRequest;
import com.vdt.student_management.academic.dto.response.FacultyDetailResponse;
import com.vdt.student_management.academic.dto.response.FacultyResponse;
import com.vdt.student_management.academic.model.Faculty;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface FacultyMapper {

    @Mapping(target = "majors", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "teachers", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    @Mapping(target = "dean", ignore = true)
    Faculty toFaculty(AddFacultyRequest request);

    FacultyResponse toFacultyResponse(Faculty faculty);

    FacultyDetailResponse toFacultyDetailResponse(Faculty faculty);
}
