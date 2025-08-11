package com.vdt.student_management.academic.mapper;

import com.vdt.student_management.academic.dto.request.AddProgramRequest;
import com.vdt.student_management.academic.dto.response.ProgramResponse;
import com.vdt.student_management.academic.model.Program;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProgramMapper {

    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "major", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    Program toProgram(AddProgramRequest request);

    ProgramResponse toProgramResponse(Program program);
}
