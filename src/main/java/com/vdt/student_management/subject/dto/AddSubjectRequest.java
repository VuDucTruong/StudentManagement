package com.vdt.student_management.subject.dto;

public record AddSubjectRequest(
    String name,
    int credits,
    String semester,
    String description,
    String subjectCode
) {

}
