package com.vdt.student_management.tuition.dto.request;

import java.time.LocalDate;

public record TuitionFeeRequest(
    Integer academicYear,
    Long creditFee,
    Long fixedFee,
    LocalDate effectiveDate,
    LocalDate expiryDate,
    Long programId
) {

}
