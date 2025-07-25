package com.vdt.student_management.academic.dto.request;

public record AddStudentClassRequest(
    String name,
    Integer startYear,
    Integer endYear
) {

}
