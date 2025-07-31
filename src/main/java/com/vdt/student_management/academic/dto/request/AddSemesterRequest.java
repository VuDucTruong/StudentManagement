package com.vdt.student_management.academic.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public record AddSemesterRequest(
    @NotEmpty(message = "SEMESTER_NAME_REQUIRED")
    String name,

    @NotNull(message = "START_DATE_REQUIRED")
    LocalDate startDate,
    @NotNull(message = "END_DATE_REQUIRED")
    LocalDate endDate
) {

}
