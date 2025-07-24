package com.vdt.student_management.academic.dto.request;

public record AddPrerequisiteRequest(
    Long subjectId,
    Long prerequisiteId,
    boolean passRequirement
) {

}
