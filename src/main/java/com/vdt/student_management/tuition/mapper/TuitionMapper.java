package com.vdt.student_management.tuition.mapper;

import com.vdt.student_management.academic.mapper.SemesterMapper;
import com.vdt.student_management.tuition.dto.response.TuitionResponse;
import com.vdt.student_management.tuition.model.Tuition;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {SemesterMapper.class})
public interface TuitionMapper {
  TuitionResponse toTuitionResponse(Tuition tuition);
}
