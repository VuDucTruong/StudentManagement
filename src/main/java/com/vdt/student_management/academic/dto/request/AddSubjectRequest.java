package com.vdt.student_management.academic.dto.request;

public record AddSubjectRequest(
    String name,
    int credits,
    String semester,
    String description,
    String subjectCode
) {

}
