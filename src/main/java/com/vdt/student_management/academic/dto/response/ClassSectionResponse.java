package com.vdt.student_management.academic.dto.response;

import java.time.LocalDate;

public record ClassSectionResponse(
    Long id,
    String name,
    LocalDate startDate,
    LocalDate endDate,
    SubjectResponse subject,
    int numOfStudents,
    TeacherResponse teacher,
    SemesterResponse semester
) {

}
