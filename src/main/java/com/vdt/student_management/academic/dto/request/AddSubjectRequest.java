package com.vdt.student_management.academic.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record AddSubjectRequest(
        @NotEmpty(message = "SUBJECT_NAME_REQUIRED") String name,
        int credits,
        String description,
        @NotEmpty(message = "SUBJECT_CODE_REQUIRED") String subjectCode,
        @NotNull(message = "MAJOR_REQUIRED") Long majorId) {}
