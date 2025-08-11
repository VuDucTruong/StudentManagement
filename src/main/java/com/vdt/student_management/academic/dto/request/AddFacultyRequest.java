package com.vdt.student_management.academic.dto.request;

import jakarta.validation.constraints.NotBlank;

public record AddFacultyRequest(@NotBlank(message = "FACULTY_NAME_REQUIRED") String name, Long deanId) {}
