package com.vdt.student_management.academic.dto.response;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record TeacherDetailResponse(
    Long id,
    String name,
    String degree,
    String specialization,
    String email,
    String phone,
    LocalDate hireDate,
    LocalDate dob,
    FacultyResponse faculty,
    LocalDateTime updatedAt,
    LocalDateTime deletedAt
) {

}
