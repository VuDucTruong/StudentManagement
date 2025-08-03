package com.vdt.student_management.academic.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AddMajorRequest(
    @NotBlank(message = "MAJOR_REQUIRED")
    String name,

    @NotNull(message = "FACULTY_REQUIRED")
    Long facultyId
) {

}
