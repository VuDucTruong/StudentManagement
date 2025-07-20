package com.vdt.student_management.academic.dto.request;

import java.time.LocalDate;

public record AddClassSectionRequest(
    String name,
    LocalDate startDate,
    LocalDate endDate,
    Long subjectId,
    Long teacherId,
    Long scheduleId,
    Long semesterId
) {

}
