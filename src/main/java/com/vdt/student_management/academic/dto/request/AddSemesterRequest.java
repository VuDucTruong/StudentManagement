package com.vdt.student_management.academic.dto.request;

import java.time.LocalDate;

public record AddSemesterRequest(
    String name,
    LocalDate startDate,
    LocalDate endDate
) {

}
