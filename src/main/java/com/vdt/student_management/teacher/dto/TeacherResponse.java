package com.vdt.student_management.teacher.dto;

public record TeacherResponse (
    Long id,
    String name,
    String degree,
    String email,
    String phone
) {}
