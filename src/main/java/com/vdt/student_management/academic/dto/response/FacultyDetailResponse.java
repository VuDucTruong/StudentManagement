package com.vdt.student_management.academic.dto.response;

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
