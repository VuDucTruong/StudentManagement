package com.vdt.student_management.academic.dto.response;

public record PrerequisiteResponse(
        SubjectResponse subject, SubjectResponse prerequisiteSubject, boolean passRequirement) {}
