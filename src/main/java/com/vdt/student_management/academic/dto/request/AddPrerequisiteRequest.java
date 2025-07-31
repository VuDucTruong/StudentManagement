package com.vdt.student_management.academic.dto.request;

import jakarta.validation.constraints.NotNull;

public record AddPrerequisiteRequest(
    @NotNull(message = "SUBJECT_REQUIRED")
    Long subjectId,
    @NotNull(message = "PREREQUISITE_REQUIRED")
    Long prerequisiteId,
    boolean passRequirement
) {

}
