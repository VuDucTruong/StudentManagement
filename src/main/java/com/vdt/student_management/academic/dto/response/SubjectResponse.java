package com.vdt.student_management.academic.dto.response;

public record SubjectResponse(
    Long id,
    String name,
    int credits,
    String description,
    String subjectCode,
    MajorResponse major
) {

}
