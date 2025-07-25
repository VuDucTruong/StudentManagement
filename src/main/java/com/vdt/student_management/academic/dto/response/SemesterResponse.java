package com.vdt.student_management.academic.dto.response;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record SemesterResponse(
    Long id,
    String name,
    LocalDate startDate,
    LocalDate endDate,
    LocalDateTime updatedAt,
    LocalDateTime deletedAt
) {

}
