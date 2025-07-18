package com.vdt.student_management.teacher.mapper;

import com.vdt.student_management.teacher.dto.AddTeacherRequest;
import com.vdt.student_management.teacher.dto.TeacherDetailResponse;
import com.vdt.student_management.teacher.dto.TeacherResponse;
import com.vdt.student_management.teacher.model.Teacher;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TeacherMapper {

  @Mapping(target = "updatedAt", ignore = true)
  @Mapping(target = "id", ignore = true)
  @Mapping(target = "faculty", ignore = true)
  @Mapping(target = "deletedAt", ignore = true)
  @Mapping(target = "classSections", ignore = true)
  Teacher toTeacher(AddTeacherRequest request);

  TeacherResponse toTeacherResponse(Teacher teacher);


  @Mapping(target = "classSection", ignore = true)
  TeacherDetailResponse toTeacherDetailResponse(Teacher teacher);
}
