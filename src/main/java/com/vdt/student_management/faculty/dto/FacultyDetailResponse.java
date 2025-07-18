package com.vdt.student_management.faculty.dto;

import com.vdt.student_management.teacher.dto.TeacherResponse;
import java.time.LocalDateTime;
import java.util.List;

public record FacultyDetailResponse(
    Long id,
    String name,
    List<TeacherResponse> teachers,
    TeacherResponse dean,
    LocalDateTime updatedAt,
    LocalDateTime deletedAt
) {

}
