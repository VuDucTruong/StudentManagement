package com.vdt.student_management.academic.mapper;

import com.vdt.student_management.academic.dto.request.AddTeacherRequest;
import com.vdt.student_management.academic.dto.response.TeacherDetailResponse;
import com.vdt.student_management.academic.dto.response.TeacherResponse;
import com.vdt.student_management.academic.model.Teacher;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {FacultyMapper.class})
public interface TeacherMapper {

  @Mapping(target = "updatedAt", ignore = true)
  @Mapping(target = "id", ignore = true)
  @Mapping(target = "faculty", ignore = true)
  @Mapping(target = "deletedAt", ignore = true)
  @Mapping(target = "classSections", ignore = true)
  Teacher toTeacher(AddTeacherRequest request);

  TeacherResponse toTeacherResponse(Teacher teacher);



  TeacherDetailResponse toTeacherDetailResponse(Teacher teacher);
}
