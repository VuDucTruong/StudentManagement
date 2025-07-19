package com.vdt.student_management.subject.dto;

import com.vdt.student_management.major.dto.response.MajorResponse;

public record SubjectResponse(
    Long id,
    String name,
    int credits,
    String semester,
    String description,
    String subjectCode,
    MajorResponse major
) {

}
