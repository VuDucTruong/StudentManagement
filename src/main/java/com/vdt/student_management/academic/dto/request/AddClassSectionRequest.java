package com.vdt.student_management.academic.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public record AddClassSectionRequest(
    @NotBlank(message = "NOT_EMPTY_SECTION_NAME")
    String name,
    @NotNull(message =  "START_DATE_REQUIRED")
    LocalDate startDate,
    @NotNull(message =  "END_DATE_REQUIRED")
    LocalDate endDate,
    @NotNull(message = "SUBJECT_REQUIRED")
    Long subjectId,
    Long teacherId,
    Long scheduleId,
    @NotNull(message = "SEMESTER_REQUIRED")
    Long semesterId
) {

}
