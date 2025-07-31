package com.vdt.student_management.academic.mapper;

import com.vdt.student_management.academic.dto.request.AddStudentRequest;
import com.vdt.student_management.academic.dto.response.StudentResponse;
import com.vdt.student_management.academic.model.Student;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface StudentMapper {

  @Mapping(target = "tuitions", ignore = true)
  @Mapping(target = "tuitionFee", ignore = true)
  @Mapping(target = "updatedAt", ignore = true)
  @Mapping(target = "status", ignore = true)
  @Mapping(target = "major", ignore = true)
  @Mapping(target = "id", ignore = true)
  @Mapping(target = "enrollments", ignore = true)
  @Mapping(target = "deletedAt", ignore = true)
  @Mapping(target = "studentClass", ignore = true)
  Student toStudent(AddStudentRequest addStudentRequest);

  StudentResponse toStudentResponse(Student student);

}
