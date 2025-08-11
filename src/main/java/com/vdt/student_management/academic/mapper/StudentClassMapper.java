package com.vdt.student_management.academic.mapper;

import com.vdt.student_management.academic.dto.request.AddStudentClassRequest;
import com.vdt.student_management.academic.dto.response.StudentClassResponse;
import com.vdt.student_management.academic.model.StudentClass;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface StudentClassMapper {

    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    StudentClass toStudentClass(AddStudentClassRequest request);

    StudentClassResponse toStudentClassResponse(StudentClass studentClass);
}
