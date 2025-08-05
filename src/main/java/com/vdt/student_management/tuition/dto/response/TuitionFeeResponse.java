package com.vdt.student_management.tuition.dto.response;

import java.time.LocalDate;

public record TuitionFeeResponse(
    Long id,
    Integer academicYear,
    Long creditFee,
    Long fixedFee,
    LocalDate effectiveDate,
    LocalDate expiryDate,
    String programName,
    LocalDate updatedAt,
    LocalDate deletedAt
    ) {

}
