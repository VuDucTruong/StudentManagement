package com.vdt.student_management.student.mapper;

import com.vdt.student_management.student.dto.request.AddStudentRequest;
import com.vdt.student_management.student.dto.response.StudentResponse;
import com.vdt.student_management.student.model.Student;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface StudentMapper {

  @Mapping(target = "updatedAt", ignore = true)
  @Mapping(target = "status", ignore = true)
  @Mapping(target = "major", ignore = true)
  @Mapping(target = "id", ignore = true)
  @Mapping(target = "enrollments", ignore = true)
  @Mapping(target = "deletedAt", ignore = true)
  Student toStudent(AddStudentRequest addStudentRequest);

  StudentResponse toStudentResponse(Student student);
}
