package com.vdt.student_management.class_section.dto;

import java.time.LocalDate;

public record AddClassSectionRequest(
    String semester,
    String name,
    LocalDate startDate,
    LocalDate endDate,
    Long subjectId,
    Long teacherId,
    Long scheduleId
) {

}
