package com.vdt.student_management.major.mapper;

import com.vdt.student_management.major.dto.response.MajorDetailResponse;
import com.vdt.student_management.major.dto.response.MajorResponse;
import com.vdt.student_management.major.model.Major;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MajorMapper {

  MajorResponse toMajorResponse(Major major);

  MajorDetailResponse toMajorDetailResponse(Major major);
}
