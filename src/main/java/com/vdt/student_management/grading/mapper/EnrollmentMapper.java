package com.vdt.student_management.grading.mapper;

import com.vdt.student_management.academic.mapper.ClassSectionMapper;
import com.vdt.student_management.academic.mapper.StudentMapper;
import com.vdt.student_management.grading.dto.response.EnrollmentResponse;
import com.vdt.student_management.grading.model.Enrollment;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {ClassSectionMapper.class, StudentMapper.class})
public interface  EnrollmentMapper {

  EnrollmentResponse toEnrollmentResponse(Enrollment enrollment);

}
