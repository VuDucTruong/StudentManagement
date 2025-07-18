package com.vdt.student_management.teacher.dto;

import com.vdt.student_management.faculty.dto.FacultyResponse;
import java.time.LocalDate;

public record TeacherResponse (
    String name,
    String degree,
    String specialization,
    String email,
    String phone,
    LocalDate hireDate,
    LocalDate dob,
    FacultyResponse faculty
) {}
