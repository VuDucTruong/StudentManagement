package com.vdt.student_management.academic.mapper;

import com.vdt.student_management.academic.dto.request.AddPrerequisiteRequest;
import com.vdt.student_management.academic.dto.response.PrerequisiteResponse;
import com.vdt.student_management.academic.model.Prerequisite;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PrerequisiteMapper {

    @Mapping(target = "subject", ignore = true)
    @Mapping(target = "prerequisiteSubject", ignore = true)
    Prerequisite toPrerequisite(AddPrerequisiteRequest request);

    PrerequisiteResponse toPrerequisiteResponse(Prerequisite prerequisite);
}
