package com.vdt.student_management.teacher.dto;

import com.vdt.student_management.faculty.dto.FacultyResponse;
import java.time.LocalDate;
import java.time.LocalDateTime;

public record TeacherResponse (
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
) {}
