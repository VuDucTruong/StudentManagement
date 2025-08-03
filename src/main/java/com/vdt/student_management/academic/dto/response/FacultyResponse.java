package com.vdt.student_management.academic.dto.response;

public record FacultyResponse(
    Long id,
    String name,
    TeacherResponse dean
) {

}
