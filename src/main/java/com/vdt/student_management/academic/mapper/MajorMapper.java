package com.vdt.student_management.academic.mapper;

import com.vdt.student_management.academic.dto.request.AddMajorRequest;
import com.vdt.student_management.academic.dto.response.MajorResponse;
import com.vdt.student_management.academic.model.Major;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(
        componentModel = "spring",
        uses = {FacultyMapper.class})
public interface MajorMapper {

    @Mapping(target = "faculty", ignore = true)
    @Mapping(target = "programs", ignore = true)
    @Mapping(target = "subjects", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    Major toMajor(AddMajorRequest addMajorRequest);

    @Mapping(target = "facultyResponse", source = "faculty")
    MajorResponse toMajorResponse(Major major);
}
