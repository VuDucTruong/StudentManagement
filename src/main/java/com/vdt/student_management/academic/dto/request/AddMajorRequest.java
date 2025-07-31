package com.vdt.student_management.academic.dto.request;

import jakarta.validation.constraints.NotBlank;

public record AddMajorRequest(
    @NotBlank(message = "MAJOR_REQUIRED")
    String name
) {

}
