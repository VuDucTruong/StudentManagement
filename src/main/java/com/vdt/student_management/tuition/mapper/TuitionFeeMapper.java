package com.vdt.student_management.tuition.mapper;

import com.vdt.student_management.academic.mapper.ProgramMapper;
import com.vdt.student_management.tuition.dto.request.TuitionFeeRequest;
import com.vdt.student_management.tuition.dto.response.TuitionFeeResponse;
import com.vdt.student_management.tuition.model.TuitionFee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(
        componentModel = "spring",
        uses = {ProgramMapper.class})
public interface TuitionFeeMapper {
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "program", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    TuitionFee toTuitionFee(TuitionFeeRequest request);

    @Mapping(target = "programName", expression = "java(tuitionFee.getProgram().getName())")
    TuitionFeeResponse toTuitionFeeResponse(TuitionFee tuitionFee);
}
